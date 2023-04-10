package br.com.quality.generator;

import br.com.quality.report.RobotReport;
import br.com.quality.report.RobotReportBuilder;
import br.com.quality.report.RobotTest;
import br.com.quality.robots.IRNRobotsTool;
import br.com.quality.robots.builder.*;
import br.com.quality.robots.support.Windows;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class GherkinGeneratorJava {
    public String path;
    public HashMap<String, ArrayList> framework = new HashMap<>();
    public HashMap<String, ArrayList> objetos = new HashMap<>();
    public HashMap<String, ArrayList> interacoes = new HashMap<>();
    public HashMap<Integer, HashMap<String, ArrayList>> massasDeDado = new HashMap<>();
    public HashMap<String, ArrayList> cenarios = new HashMap<>();
    private IRNRobotsTool robotsTool;
    private String currentFerramenta = "";
    private String currentFunctionality = "";
    static RobotReport report;
    static RobotTest test;
    static RobotTest step;
    private DesiredCapabilities cap = new DesiredCapabilities();
    private String remoteUrl = "";
    private HashMap<String, String> variables = new HashMap<>();
    private boolean isMassaDeDados = false;
    private int massaAtual = -1;
    private HashMap<String, Integer> varAtual;
    private HashMap<Integer, ArrayList<String>> operator = new HashMap<>();

    /**
     * Inicia variaveis baseadas na planilha desejada do documento desejado
     * @param workbook
     * @param sheetName
     * @param startingPoint
     * @return
     */
    private HashMap<String, ArrayList> setup(Workbook workbook, String sheetName, int startingPoint) {
        Sheet sheet = workbook.getSheet(sheetName);
        HashMap<String, ArrayList> mapa = new HashMap<>();

        Iterator<Row> rowIterator = sheet.rowIterator();
        int i = 0;
        HashMap<Integer, String> colunaNomes = new HashMap<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (i==startingPoint) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    colunaNomes.put(cell.getColumnIndex(), cell.getStringCellValue());
                }
                for (Map.Entry<Integer, String> m : colunaNomes.entrySet()){
                    mapa.put(m.getValue(), new ArrayList());
                }
            }
            if (i>startingPoint) {
                for (Map.Entry<Integer, String> m : colunaNomes.entrySet()){
                    int indexCol = m.getKey();
                    String nameCol = m.getValue();

                    Cell cell = row.getCell(indexCol);
                    if (cell!=null) {
                        String celula;
                        if (cell.getCellType().name().equals("NUMERIC"))
                            celula = cell.getNumericCellValue() + "";
                        else
                            celula = cell.getStringCellValue();
                        mapa.get(nameCol).add(celula);
                    }
                }
            }
            i++;
        }
        return mapa;
    }

    /**
     * Inicia a variavel com as massas que serão utilizadas como paramêtro
     * @param workbook
     */
    public void setupMassaDeDados(Workbook workbook) {
        Sheet sheet = workbook.getSheet("MassaDeDados");
        Iterator<Row> rowIterator = sheet.rowIterator();
        HashMap<Integer, String> colName = new HashMap<>();
        HashMap<Integer, ArrayList> colValues = new HashMap<>();
        HashMap<String, ArrayList> tbl = new HashMap<>();

        int index = -1;
        while (rowIterator.hasNext()) {
            Row row =  rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            if(row.getCell(1).toString().equals("")){
                if (!colValues.isEmpty()){
                    int id = Integer.parseInt(tbl.get("ID massa").get(0).toString());
                    massasDeDado.put(id, tbl);
                }
                index=0;
                colName = new HashMap<>();
                colValues = new HashMap<>();
                tbl = new HashMap<>();
            } else {
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (index==1) {
                        colName.put(cell.getColumnIndex(), cell.getStringCellValue());
                        tbl.put(cell.getStringCellValue(), new ArrayList());
                        colValues.put(cell.getColumnIndex(), new ArrayList());
                    } else{
                        int i = cell.getColumnIndex();
                        String value = (cell.getCellType().name().equals("NUMERIC") ? String.valueOf((int)cell.getNumericCellValue()) : cell.getStringCellValue());
                        colValues.get(i).add(value);
                        tbl.get(colName.get(i)).add(value);
                    }
                }
            }
            index++;
        }
        int id = Integer.parseInt(tbl.get("ID massa").get(0).toString());
        massasDeDado.put(id, tbl);
       // System.out.println(massasDeDado);

    }
    private String getVariable(String varName) {
        return String.valueOf(variables.get(varName));
    }

    private void setCapability(String capabilityName, String value) {
        System.out.println("Adicionando cap " + capabilityName + " com o valor " + value);
        cap.setCapability(capabilityName, value);
    }

    public String findObject(String nome) {
        for (int n = 0; n<objetos.get("Nome Completo").size();n++) {
            if (nome.equals(objetos.get("Nome Completo").get(n))){
                return objetos.get("identificador/xpath").get(n).toString();
            }
        }
        return null;
    }

    public String getMassaDeDadosVar(String key) {
        int curIndex = varAtual.get(key)-1;
       // System.out.println(massasDeDado.get(massaAtual).get(key).get(curIndex));
        return String.valueOf(massasDeDado.get(massaAtual).get(key).get(curIndex));
    }

    /**
     * Constroi e retorna o xpath a partir da string do gerador
     * @param xpath
     * @return
     */
    public String setupXpath(String xpath) {
        if (xpath==null) return null;
        String type = xpath.substring(0, xpath.indexOf("="));
        String value = xpath.substring(xpath.indexOf("=")+1);
        switch (type) {
            case "id":
                return "//*[@id=\""+ value +"\"]";
            case "className":
            case "class":
                return "//*[@class=\""+ value +"\"]";
            case "xpath":
                return value;
        }
        return null;
    }

    public String setupXpathAppium(String xpath) {
        String type = xpath.substring(0, xpath.indexOf("="));
        String value = xpath.substring(xpath.indexOf("=")+1);
        switch (type) {
            case "id":
                return "//*[@id=\""+ value +"\"]";
            case "className":
            case "class":
                return "//*[@class=\""+ value +"\"]";
            case "xpath":
                return value;
        }
        return null;
    }

}

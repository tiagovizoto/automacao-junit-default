// created by Rafael Tulio
package br.com.quality.robots.resolver;

public class XpathAutoItResolver {

	private String attach;

	public XpathAutoItResolver(String attach) {
		this.attach = attach;
	}

	public String getTitle() {
		return captureProp("TITLE");
	}

	public String getText() {
		return captureProp("TEXT");
	}

	public String getControlId() {
		return captureProp("CONTROLID");
	}

	/**
	 * This method returns a text based in the parameter.
	 * The initial point is the parameter and the method gets text after the char equals '=' until the char pipe '|'
	 * @param valueStr - The text that you want to capture the property
	 * @param prop - The property
	 * @return the text between (prop + '=') and '|'
	 */
	private String captureProp(String prop)
	{
		String ret;
		int iniProp;
		int endProp;

		if (attach == "" || attach.indexOf(prop + "=") < 0) //$NON-NLS-1$ //$NON-NLS-2$
			ret = ""; //$NON-NLS-1$
		else
		{
			iniProp = attach.indexOf(prop + "="); //$NON-NLS-1$
			endProp = attach.indexOf("|", iniProp ); //$NON-NLS-1$
			if (endProp < iniProp)
				endProp = attach.length();
			else
				endProp = attach.indexOf("|", iniProp); //$NON-NLS-1$

			iniProp = iniProp + (prop.length() +1);
			ret = attach.substring(iniProp, endProp);
		}

		return ret;
	}
}

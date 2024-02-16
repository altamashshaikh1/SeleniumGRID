/**
 *  This class consists all supported Maven Arguments values defined.
 *  
 * @author altsh
 * @version 1.0
 * @since 21st August 2023
 **/

package com.webMethods.io.Integration.Constants;

import java.util.Arrays;
import java.util.List;

public class SupportedMavenArgumentValues 
{
	/** SUPPORTED ENVIRONMENTS VALUES **/
	public static final List<String> SUPPORTED_ENVIRONMENTS = Arrays.asList("ComponentBVT","PreDev","Developement","Integration","Stag","Spro","PreProd","Production");

	/** SUPPORTED BROWSERS VALUES **/
	public static final List<String> SUPPORTED_BROWSERS = Arrays.asList("chrome","chromeheadless","firefox","firefoxheadless","edge","edgeheadless","opera","operaheadless","safari","internetexplorer");

	/** SUPPORTED TESTNG XML FILE NAME **/
	public static final List<String> SUPPORTED_XML_FILE_NAMES = Arrays.asList("LocalTest.xml","ComponentBVT.xml","PreDev.xml","Developement.xml","Integration.xml","Stag.xml","Spro.xml","PreProd.xml","Production.xml","TestSuiteCloudCycle.xml","TestSuiteInfraUpdate.xml");
}
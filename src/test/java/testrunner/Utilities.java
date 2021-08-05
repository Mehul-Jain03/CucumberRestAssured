package testrunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {

	static RequestSpecification reqSpec;
	static ResponseSpecification resSpec;

	public RequestSpecification requestSpecification() throws IOException {
		if (reqSpec == null) {

			PrintStream loggingObject = new PrintStream(new FileOutputStream("logging.txt"));

			reqSpec = new RequestSpecBuilder().setBaseUri(getBaseUrlValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(loggingObject))
					.addFilter(ResponseLoggingFilter.logResponseTo(loggingObject)).setContentType(ContentType.JSON)
					.build();
			return reqSpec;
		}
		return reqSpec;

	}

	public ResponseSpecification responseSpecification() {

		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return resSpec;

	}

	public static String getBaseUrlValue(String key) throws IOException {

		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				"/home/mehuljain/eclipse-workspace/CucumberRestAssured/src/test/java/testrunner/global.properties");
		properties.load(fis);
		String value = properties.getProperty(key);
		return value;
	}

	public Object getJsonPath(Response response, String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(key).toString();

	}
}
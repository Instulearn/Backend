package utilities.API_Utilities;

import java.util.HashMap;

public class TestData{
    HashMap<String, Object> requestBody;

    public HashMap updateCategoryRequestBody() {

        requestBody = new HashMap<>();

        requestBody.put("title", "Education and Training");

        return requestBody;
    }


}

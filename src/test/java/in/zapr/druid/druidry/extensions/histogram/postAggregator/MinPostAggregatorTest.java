/*
 * Copyright 2018-present Red Brick Lane Marketing Solutions Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.zapr.druid.druidry.extensions.histogram.postAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MinPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {
        MinPostAggregator minPostAggregator
                = new MinPostAggregator("CarpeDiem", "Seize the Day");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "min");
        jsonObject.put("name", "CarpeDiem");
        jsonObject.put("fieldName", "Seize the Day");

        String actualJSON = objectMapper.writeValueAsString(minPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingNameFields() {
        MinPostAggregator minPostAggregator
                = new MinPostAggregator(null, "Seize the day");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingFieldNameFields() {
        MinPostAggregator minPostAggregator
                = new MinPostAggregator("CarpeDiem", null);
    }
}

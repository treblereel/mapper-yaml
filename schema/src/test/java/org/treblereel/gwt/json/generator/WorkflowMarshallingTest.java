/*
 * Copyright © 2025 Treblereel
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
package org.treblereel.gwt.json.generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.treblereel.gwt.json.generator.workflow.*;

class WorkflowMarshallingTest {

  @Test
  void schemaRoundTrip() throws Exception {
    Schema schema = new Schema();
    schema.setFormat("json");

    String yaml = Schema_YamlMapperImpl.INSTANCE.write(schema);
    assertTrue(yaml.contains("format:") && yaml.contains("json"));

    Schema result = Schema_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals("json", result.getFormat());
  }

  @Test
  void inputWithNestedSchemaRoundTrip() throws Exception {
    Schema schema = new Schema();
    schema.setFormat("yaml");

    Input input = new Input();
    input.setSchema(schema);

    String yaml = Input_YamlMapperImpl.INSTANCE.write(input);
    assertTrue(yaml.contains("format:") && yaml.contains("yaml"));

    Input result = Input_YamlMapperImpl.INSTANCE.read(yaml);
    assertNotNull(result.getSchema());
    assertEquals("yaml", result.getSchema().getFormat());
  }

  @Test
  void externalResourceRoundTrip() throws Exception {
    ExternalResource resource = new ExternalResource();
    resource.setName("my-api");

    String yaml = ExternalResource_YamlMapperImpl.INSTANCE.write(resource);
    assertTrue(yaml.contains("name:") && yaml.contains("my-api"));

    ExternalResource result = ExternalResource_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals("my-api", result.getName());
  }

  @Test
  void eventPropertiesRoundTrip() throws Exception {
    EventProperties props = new EventProperties();
    props.setId("evt-001");
    props.setType("com.example.event");
    props.setSubject("test-subject");
    props.setDatacontenttype("application/json");

    String yaml = EventProperties_YamlMapperImpl.INSTANCE.write(props);
    assertTrue(yaml.contains("id:") && yaml.contains("evt-001"));
    assertTrue(yaml.contains("type:") && yaml.contains("com.example.event"));

    EventProperties result = EventProperties_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals("evt-001", result.getId());
    assertEquals("com.example.event", result.getType());
    assertEquals("test-subject", result.getSubject());
    assertEquals("application/json", result.getDatacontenttype());
  }

  @Test
  void oauth2WithEnumAndListsRoundTrip() throws Exception {
    Oauth2AuthenticationProperties oauth = new Oauth2AuthenticationProperties();
    oauth.setGrant(Grant.CLIENT_CREDENTIALS);
    oauth.setUsername("user1");
    oauth.setPassword("secret");
    oauth.setScopes(List.of("read", "write"));
    oauth.setAudiences(List.of("api.example.com"));
    oauth.setIssuers(List.of("https://auth.example.com"));

    Oauth2Token subject = new Oauth2Token();
    subject.setToken("subj-token");
    subject.setType("urn:ietf:params:oauth:token-type:jwt");
    oauth.setSubject(subject);

    String yaml = Oauth2AuthenticationProperties_YamlMapperImpl.INSTANCE.write(oauth);
    assertTrue(yaml.contains("grant:") && yaml.contains("CLIENT_CREDENTIALS"));
    assertTrue(yaml.contains("username:") && yaml.contains("user1"));
    assertTrue(yaml.contains("scopes:") && yaml.contains("read") && yaml.contains("write"));
    assertTrue(yaml.contains("token:") && yaml.contains("subj-token"));

    Oauth2AuthenticationProperties result =
        Oauth2AuthenticationProperties_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals(Grant.CLIENT_CREDENTIALS, result.getGrant());
    assertEquals("user1", result.getUsername());
    assertEquals(List.of("read", "write"), result.getScopes());
    assertNotNull(result.getSubject());
    assertEquals("subj-token", result.getSubject().getToken());
  }

  @Test
  void retryPolicyRoundTrip() throws Exception {
    RetryPolicy policy = new RetryPolicy();
    policy.setWhen("error");
    policy.setExceptWhen("timeout");

    String yaml = RetryPolicy_YamlMapperImpl.INSTANCE.write(policy);
    assertTrue(yaml.contains("when:") && yaml.contains("error"));
    assertTrue(yaml.contains("exceptWhen:") && yaml.contains("timeout"));

    RetryPolicy result = RetryPolicy_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals("error", result.getWhen());
    assertEquals("timeout", result.getExceptWhen());
  }

  @Test
  void errorWithStatusRoundTrip() throws Exception {
    org.treblereel.gwt.json.generator.workflow.Error error =
        new org.treblereel.gwt.json.generator.workflow.Error();
    error.setStatus(404);

    String yaml = Error_YamlMapperImpl.INSTANCE.write(error);
    assertTrue(yaml.contains("status:") && yaml.contains("404"));

    org.treblereel.gwt.json.generator.workflow.Error result =
        Error_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals(404, result.getStatus());
  }

  @Test
  void rootWithInputOutputRoundTrip() throws Exception {
    Schema inputSchema = new Schema();
    inputSchema.setFormat("json");
    Input input = new Input();
    input.setSchema(inputSchema);

    Schema outputSchema = new Schema();
    outputSchema.setFormat("yaml");
    Output output = new Output();
    output.setSchema(outputSchema);

    Root root = new Root();
    root.setInput(input);
    root.setOutput(output);

    String yaml = Root_YamlMapperImpl.INSTANCE.write(root);
    Root result = Root_YamlMapperImpl.INSTANCE.read(yaml);

    assertNotNull(result.getInput());
    assertEquals("json", result.getInput().getSchema().getFormat());
    assertNotNull(result.getOutput());
    assertEquals("yaml", result.getOutput().getSchema().getFormat());
  }

  @Test
  void grantEnumValues() {
    assertNotNull(Grant.AUTHORIZATION_CODE);
    assertNotNull(Grant.CLIENT_CREDENTIALS);
    assertNotNull(Grant.PASSWORD);
    assertNotNull(Grant.REFRESH_TOKEN);
    assertNotNull(Grant.URN_IETF_PARAMS_OAUTH_GRANT_TYPE_TOKEN_EXCHANGE);
  }

  @Test
  void yamlRoundTripPreservesAllFields() throws Exception {
    Oauth2Token token = new Oauth2Token();
    token.setToken("abc123");
    token.setType("bearer");

    String yaml = Oauth2Token_YamlMapperImpl.INSTANCE.write(token);
    Oauth2Token result = Oauth2Token_YamlMapperImpl.INSTANCE.read(yaml);
    String yaml2 = Oauth2Token_YamlMapperImpl.INSTANCE.write(result);

    assertEquals(yaml, yaml2);
  }

  @Test
  void externalResourceEndpointIsObjectType() throws Exception {
    ExternalResource resource = new ExternalResource();
    resource.setName("my-api");
    resource.setEndpoint(null);

    String yaml = ExternalResource_YamlMapperImpl.INSTANCE.write(resource);
    assertTrue(yaml.contains("name:") && yaml.contains("my-api"));

    ExternalResource result = ExternalResource_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals("my-api", result.getName());
  }

  @Test
  void httpSerializesWithObjectEndpoint() throws Exception {
    Http http = new Http();
    http.setEndpoint("https://api.example.com");

    String yaml = Http_YamlMapperImpl.INSTANCE.write(http);
    assertTrue(
        yaml.contains("endpoint:") && yaml.contains("https://api.example.com"),
        "YAML should contain endpoint value: " + yaml);
  }

  @Test
  void containerLifetimeRoundTrip() throws Exception {
    ContainerLifetime lifetime = new ContainerLifetime();
    lifetime.setCleanup(Cleanup.ALWAYS);

    String yaml = ContainerLifetime_YamlMapperImpl.INSTANCE.write(lifetime);
    assertTrue(yaml.contains("cleanup:") && yaml.contains("ALWAYS"));

    ContainerLifetime result = ContainerLifetime_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals(Cleanup.ALWAYS, result.getCleanup());
  }

  @Test
  void retryPolicyDelayIsObjectType() throws Exception {
    RetryPolicy policy = new RetryPolicy();
    policy.setWhen("error");
    policy.setDelay(null);

    String yaml = RetryPolicy_YamlMapperImpl.INSTANCE.write(policy);
    RetryPolicy result = RetryPolicy_YamlMapperImpl.INSTANCE.read(yaml);
    assertEquals("error", result.getWhen());
  }
}

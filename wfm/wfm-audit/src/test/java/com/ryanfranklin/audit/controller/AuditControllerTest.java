package com.ryanfranklin.audit.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanfranklin.audit.model.Audit;
import com.ryanfranklin.audit.model.AuditAction;
import com.ryanfranklin.audit.model.AuditEntity;
import com.ryanfranklin.audit.model.AuditSearch;
import com.ryanfranklin.audit.service.AuditService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class AuditControllerTest {

  private static String URL_PATH = "/audit";
  private static long DEFAULT_AUDIT_ID = 0;
  private static long ENTITY_ID = 123456;
  private static AuditEntity ENTITY_EMPLOYEE = AuditEntity.EMPLOYEE;
  private static AuditAction ACTION_CREATE = AuditAction.CREATE;
  private static long UPDATE_EPOCH_MILLI = Instant.now().toEpochMilli();

  private static String ENTITY_QUERY_PARAM_KEY = "entity";
  private static String ACTION_QUERY_PARAM_KEY = "action";
  private static String ENTITY_QUERY_PARAM_VALUE = "employee";
  private static String ACTION_QUERY_PARAM_VALUE = "create";

  private MockMvc mockMvc;

  @Mock
  private AuditService auditService;

  @InjectMocks
  private AuditController auditController;

  private JacksonTester<List<Audit>> jacksonAuditTester;
  private Audit audit;
  private List<Audit> audits;
  AuditSearch searchByEntity;
  AuditSearch searchByAction;



  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
    mockMvc = MockMvcBuilders.standaloneSetup(auditController)
        .setControllerAdvice(new AuditControllerExceptionHandler())
        .build();

    audit = new Audit(
        ENTITY_ID,
        ENTITY_EMPLOYEE,
        ACTION_CREATE,
        UPDATE_EPOCH_MILLI
    );

//    audits = new ArrayList<>();
//    audits.add(audit);

    searchByEntity = new AuditSearch();
    searchByEntity.setEntity(AuditEntity.EMPLOYEE);

    searchByAction = new AuditSearch();
    searchByAction.setAction(AuditAction.CREATE);
  }


  @Test
  public void getAuditByIdSuccess() throws Exception {
    List<Audit> audits = new ArrayList<>();
    audits.add(audit);

    given(auditService.getAudits(null)).willReturn(audits);

    MockHttpServletResponse response = mockMvc.perform(get(URL_PATH)
        .param(ENTITY_QUERY_PARAM_KEY, ENTITY_QUERY_PARAM_VALUE)
        .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

//    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    assertThat(response.getContentAsString()).isEqualTo(
//        jacksonAuditTester.write(audits).getJson());
  }
}
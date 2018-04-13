package com.ryanfranklin.audit.events;

import com.ryanfranklin.audit.model.Audit;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class AuditDeserializer extends JsonDeserializer<Audit> {

	public AuditDeserializer() {
		super(Audit.class);
	}

}

package co.com.javeriana.soap.integracion.QueueLogService;

import org.apache.camel.Exchange;
import org.queuelog.demo.Enqueuelog;
import org.queuelog.demo.EnqueuelogResponse;
import org.springframework.stereotype.Component;

import co.com.javeriana.soap.integracion.util.Utils;

@Component
public class SoapService {

	public void enqueueLogMessage(final Exchange exchange) throws InterruptedException {
		Enqueuelog datos = exchange.getIn().getBody(Enqueuelog.class);
		StringBuilder datosLog = new StringBuilder();
		datosLog.append(datos.getFechaEvento() + "-->").append(datos.getClase() + "-->")
				.append(datos.getNivel() + "-->").append(datos.getObservacion() + "<--")
				.append(System.getProperty("line.separator"));
		exchange.getOut().setBody(datosLog.toString());
	}

	public void enqueueLogMessageResponse(final Exchange exchange) throws InterruptedException {
		EnqueuelogResponse response = new EnqueuelogResponse();
		response.setCodigoEstado("1");
		response.setEstado("OK");
		response.setFechaRespuesta(Utils.getFechaEvento());
		response.setMotivoEstado("LOG ALMACENADO");
		exchange.getOut().setBody(response);
	}
}

package com.atonin.task.service;

import com.atonin.task.model.Payment;
import com.atonin.task.model.Response;
import com.atonin.task.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Override
    public String addOrUpdate(Payment payment){
        paymentRepository.save(payment);
        Response response = new Response(payment.getId(),payment.getAccount(),1,"PAYMENT CONFIRMED",payment.getDate());
        return marshal(response);
    }

    @Override
    public String findAccount(Payment payment)  {

        Response response = null;
        if(payment.getAccount() == null || payment.getId() == null){
            response = new Response("Account number or id is null");
        } else {
            Optional<Payment> pay = paymentRepository.findById(payment.getId());
            if (pay.isPresent()){
                String message = (pay.get().getAccount()
                        .equals(payment.getAccount()) ) ? "ACCOUNT EXISTS" : "ACCOUNT DOES NOT EXIST";
                response = new Response(payment.getId()
                        , payment.getAccount()
                        , (message.equals("ACCOUNT EXISTS")?1:0)
                        , message
                        , payment.getDate());
            }
        }
        return marshal(response);
    }

    public String marshal(Response response){

        JAXBContext context = null;
        StringWriter sw = new StringWriter();
        try {
            context = JAXBContext.newInstance(Response.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.marshal(response, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}

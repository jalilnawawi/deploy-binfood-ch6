package com.example.challenge4.controller;

import com.example.challenge4.dto.orderDetail.OrderDetailReportDto;
import com.example.challenge4.service.InvoiceServiceFacade;
import com.example.challenge4.service.JasperService;

import net.sf.jasperreports.engine.JRException;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("report")
public class ReportingController {
    @Autowired
    InvoiceServiceFacade invoiceServiceFacade;

    @Autowired
    JasperService jasperService;

    @GetMapping("/generate/{format}/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Resource> getAll(@PathVariable String format,@PathVariable UUID userId) throws JRException {
        OrderDetailReportDto orderDetailReportDto = invoiceServiceFacade.reportInvoice(userId);
        byte[] reportContent = jasperService.getOrderReport(orderDetailReportDto, format);

        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("order-report."+format).build().toString())
                .body(resource);
    }
}

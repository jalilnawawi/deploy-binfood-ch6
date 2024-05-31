package com.example.challenge4.service;

import com.example.challenge4.dto.orderDetail.OrderDetailReportDto;
import net.sf.jasperreports.engine.JRException;

public interface JasperService {
    byte[] getOrderReport(OrderDetailReportDto orderDetailReportDto, String format);
}

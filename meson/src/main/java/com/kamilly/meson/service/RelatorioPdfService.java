package com.kamilly.meson.service;

import com.kamilly.meson.dto.response.ProdutoMaisVendidoDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioPdfService {
    public byte[] gerarRelatorioProdutosMaisVendidos(List<ProdutoMaisVendidoDTO> dados) throws Exception {
        InputStream stream = getClass().getResourceAsStream("/relatorios/produtos_mais_vendidos.jrxml");
        JasperReport report = JasperCompileManager.compileReport(stream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
        JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }

}

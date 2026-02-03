package com.kamilly.meson.service.impl;

import com.kamilly.meson.service.ImagemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImagemServiceImpl implements ImagemService {

        private final String PASTA_UPLOAD = "uploads/";

        @Override
        public String salvarImagem(MultipartFile arquivo) {

            if (arquivo == null || arquivo.isEmpty()) {
                return null; // ou retorna imagem padrão
            }

            try {
                Path pastaPath = Paths.get(PASTA_UPLOAD);
                if (!Files.exists(pastaPath)) {
                    Files.createDirectories(pastaPath);
                }

                String nomeOriginal = arquivo.getOriginalFilename();
                String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
                String nomeArquivo = UUID.randomUUID() + extensao;

                Path caminhoFinal = pastaPath.resolve(nomeArquivo);
                Files.copy(arquivo.getInputStream(), caminhoFinal);

                return "/uploads/" + nomeArquivo;

            } catch (IOException e) {
                throw new RuntimeException("Erro ao salvar imagem", e);
            }
        }
}

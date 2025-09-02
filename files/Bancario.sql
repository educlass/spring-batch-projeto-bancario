CREATE TABLE arquivo_processado (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome_arquivo VARCHAR(255) NOT NULL,
  tipo_arquivo VARCHAR(50),
  data_recebimento DATETIME NOT NULL,
  status VARCHAR(20) DEFAULT 'PENDENTE',
  observacao TEXT,
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lancamento_bancario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_arquivo BIGINT,
  data_lancamento DATE,
  descricao VARCHAR(255),
  valor DECIMAL(15,2),
  tipo VARCHAR(20), -- crédito ou débito
  status VARCHAR(20) DEFAULT 'VALIDO',
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_arquivo) REFERENCES arquivo_processado(id)
);

CREATE TABLE log_execucao_batch (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome_job VARCHAR(100),
  data_inicio DATETIME,
  data_fim DATETIME,
  status VARCHAR(20),
  registros_processados INT,
  registros_com_erro INT,
  mensagem TEXT
);


CREATE TABLE erro_lancamento (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_lancamento BIGINT,
  
  motivo TEXT,
  registrado_em DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_lancamento) REFERENCES lancamento_bancario(id)
);


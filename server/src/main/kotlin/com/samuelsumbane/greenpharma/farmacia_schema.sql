-- ============================================================
--  SISTEMA DE FARMÁCIA — Schema MySQL
--  Gerado por
-- ============================================================

CREATE DATABASE IF NOT EXISTS farmacia
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE farmacia;

-- ------------------------------------------------------------
-- CATEGORIAS
-- ------------------------------------------------------------
CREATE TABLE categorias (
  id            CHAR(36)      NOT NULL DEFAULT (UUID()),
  nome          VARCHAR(100)  NOT NULL,
  descricao     TEXT,
  requer_receita TINYINT(1)   NOT NULL DEFAULT 0,
  controlado    TINYINT(1)    NOT NULL DEFAULT 0,
  created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_categorias_nome (nome)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- FORNECEDORES
-- ------------------------------------------------------------
CREATE TABLE fornecedores (
  id            CHAR(36)      NOT NULL DEFAULT (UUID()),
  nome          VARCHAR(150)  NOT NULL,
  nuit          VARCHAR(20),
  contacto      VARCHAR(100),
  telefone      VARCHAR(20),
  email         VARCHAR(100),
  endereco      VARCHAR(255),
  ativo         TINYINT(1)    NOT NULL DEFAULT 1,
  created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_fornecedores_nuit (nuit)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- PRODUTOS
-- ------------------------------------------------------------
CREATE TABLE produtos (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  categoria_id    CHAR(36)      NOT NULL,
  nome_comercial  VARCHAR(150)  NOT NULL,
  nome_generico   VARCHAR(150),
  principio_ativo VARCHAR(150),
  fabricante      VARCHAR(100),
  codigo_barras   VARCHAR(50),
  unidade         VARCHAR(20)   NOT NULL DEFAULT 'unidade',  -- unidade, caixa, frasco, ml, mg
  preco_venda     DECIMAL(10,2) NOT NULL,
  preco_custo     DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  stock_minimo    INT           NOT NULL DEFAULT 0,
  stock_atual     INT           NOT NULL DEFAULT 0,
  ativo           TINYINT(1)    NOT NULL DEFAULT 1,
  created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_produtos_barcode (codigo_barras),
  KEY idx_produtos_categoria (categoria_id),
  KEY idx_produtos_nome (nome_comercial),
  CONSTRAINT fk_produtos_categoria
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- LOTES
-- ------------------------------------------------------------
CREATE TABLE lotes (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  produto_id      CHAR(36)      NOT NULL,
  fornecedor_id   CHAR(36),
  numero_lote     VARCHAR(50)   NOT NULL,
  validade        DATE          NOT NULL,
  quantidade      INT           NOT NULL DEFAULT 0,
  preco_custo     DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  data_entrada    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_lotes_produto (produto_id),
  KEY idx_lotes_validade (validade),
  CONSTRAINT fk_lotes_produto
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
  CONSTRAINT fk_lotes_fornecedor
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- CLIENTES
-- ------------------------------------------------------------
CREATE TABLE clientes (
  id                CHAR(36)      NOT NULL DEFAULT (UUID()),
  nome              VARCHAR(150)  NOT NULL,
  nuit              VARCHAR(20),
  bi                VARCHAR(20),
  telefone          VARCHAR(20),
  email             VARCHAR(100),
  data_nascimento   DATE,
  genero            ENUM('M','F','Outro'),
  endereco          VARCHAR(255),
  alergias          TEXT,
  observacoes       TEXT,
  pontos_fidelidade INT           NOT NULL DEFAULT 0,
  ativo             TINYINT(1)    NOT NULL DEFAULT 1,
  created_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_clientes_nome (nome),
  KEY idx_clientes_telefone (telefone)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- FUNCIONÁRIOS
-- ------------------------------------------------------------
CREATE TABLE funcionarios (
  id          CHAR(36)      NOT NULL DEFAULT (UUID()),
  nome        VARCHAR(150)  NOT NULL,
  cargo       ENUM('admin','farmaceutico','tecnico','caixa') NOT NULL DEFAULT 'caixa',
  username    VARCHAR(50)   NOT NULL,
  senha_hash  VARCHAR(255)  NOT NULL,
  telefone    VARCHAR(20),
  email       VARCHAR(100),
  ativo       TINYINT(1)    NOT NULL DEFAULT 1,
  created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uq_funcionarios_username (username)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- RECEITAS MÉDICAS
-- ------------------------------------------------------------
CREATE TABLE receitas (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  cliente_id      CHAR(36),
  medico_nome     VARCHAR(150),
  medico_ordem    VARCHAR(50),   -- número de ordem do médico
  data_emissao    DATE          NOT NULL,
  data_validade   DATE,
  observacoes     TEXT,
  imagem_url      VARCHAR(255), -- foto/scan da receita
  created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_receitas_cliente (cliente_id),
  CONSTRAINT fk_receitas_cliente
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- CAIXA
-- ------------------------------------------------------------
CREATE TABLE caixa (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  funcionario_id  CHAR(36)      NOT NULL,
  abertura        TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fechamento      TIMESTAMP,
  saldo_inicial   DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  saldo_final     DECIMAL(10,2),
  observacoes     TEXT,
  status          ENUM('aberto','fechado') NOT NULL DEFAULT 'aberto',
  PRIMARY KEY (id),
  KEY idx_caixa_funcionario (funcionario_id),
  KEY idx_caixa_status (status),
  CONSTRAINT fk_caixa_funcionario
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- VENDAS
-- ------------------------------------------------------------
CREATE TABLE vendas (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  cliente_id      CHAR(36),
  funcionario_id  CHAR(36)      NOT NULL,
  receita_id      CHAR(36),
  caixa_id        CHAR(36),
  data_venda      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  subtotal        DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  desconto        DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  total           DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  status          ENUM('pendente','concluida','cancelada') NOT NULL DEFAULT 'concluida',
  observacoes     TEXT,
  created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_vendas_cliente (cliente_id),
  KEY idx_vendas_funcionario (funcionario_id),
  KEY idx_vendas_data (data_venda),
  KEY idx_vendas_status (status),
  CONSTRAINT fk_vendas_cliente
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
  CONSTRAINT fk_vendas_funcionario
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
  CONSTRAINT fk_vendas_receita
    FOREIGN KEY (receita_id) REFERENCES receitas(id),
  CONSTRAINT fk_vendas_caixa
    FOREIGN KEY (caixa_id) REFERENCES caixa(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- ITENS DA VENDA
-- ------------------------------------------------------------
CREATE TABLE venda_itens (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  venda_id        CHAR(36)      NOT NULL,
  produto_id      CHAR(36)      NOT NULL,
  lote_id         CHAR(36),
  quantidade      INT           NOT NULL,
  preco_unitario  DECIMAL(10,2) NOT NULL,
  desconto        DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  subtotal        DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  KEY idx_vitens_venda (venda_id),
  KEY idx_vitens_produto (produto_id),
  CONSTRAINT fk_vitens_venda
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE,
  CONSTRAINT fk_vitens_produto
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
  CONSTRAINT fk_vitens_lote
    FOREIGN KEY (lote_id) REFERENCES lotes(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- PAGAMENTOS
-- ------------------------------------------------------------
CREATE TABLE pagamentos (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  venda_id        CHAR(36)      NOT NULL,
  forma_pagamento ENUM('dinheiro','mpesa','emola','cartao','transferencia','credito') NOT NULL,
  valor           DECIMAL(10,2) NOT NULL,
  troco           DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  referencia      VARCHAR(100), -- número da transação M-Pesa, etc.
  data_pagamento  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_pagamentos_venda (venda_id),
  CONSTRAINT fk_pagamentos_venda
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- ORDENS DE COMPRA
-- ------------------------------------------------------------
CREATE TABLE ordens_compra (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  fornecedor_id   CHAR(36)      NOT NULL,
  funcionario_id  CHAR(36)      NOT NULL,
  data_pedido     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  data_entrega    DATE,
  total           DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  status          ENUM('rascunho','enviado','recebido','cancelado') NOT NULL DEFAULT 'rascunho',
  observacoes     TEXT,
  created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_ordens_fornecedor (fornecedor_id),
  KEY idx_ordens_status (status),
  CONSTRAINT fk_ordens_fornecedor
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id),
  CONSTRAINT fk_ordens_funcionario
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- ITENS DA ORDEM DE COMPRA
-- ------------------------------------------------------------
CREATE TABLE ordens_compra_itens (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  ordem_id        CHAR(36)      NOT NULL,
  produto_id      CHAR(36)      NOT NULL,
  quantidade      INT           NOT NULL,
  preco_unitario  DECIMAL(10,2) NOT NULL,
  subtotal        DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id),
  KEY idx_ocitens_ordem (ordem_id),
  CONSTRAINT fk_ocitens_ordem
    FOREIGN KEY (ordem_id) REFERENCES ordens_compra(id) ON DELETE CASCADE,
  CONSTRAINT fk_ocitens_produto
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- MOVIMENTOS DE STOCK (auditoria de entradas/saídas)
-- ------------------------------------------------------------
CREATE TABLE stock_movimentos (
  id              CHAR(36)      NOT NULL DEFAULT (UUID()),
  produto_id      CHAR(36)      NOT NULL,
  lote_id         CHAR(36),
  funcionario_id  CHAR(36),
  tipo            ENUM('entrada','saida','ajuste','devolucao') NOT NULL,
  quantidade      INT           NOT NULL,
  stock_anterior  INT           NOT NULL,
  stock_posterior INT           NOT NULL,
  referencia_id   CHAR(36),     -- id da venda ou ordem_compra que originou
  referencia_tipo VARCHAR(50),  -- 'venda' ou 'ordem_compra'
  observacoes     TEXT,
  created_at      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_smov_produto (produto_id),
  KEY idx_smov_data (created_at),
  CONSTRAINT fk_smov_produto
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
  CONSTRAINT fk_smov_lote
    FOREIGN KEY (lote_id) REFERENCES lotes(id),
  CONSTRAINT fk_smov_funcionario
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
) ENGINE=InnoDB;

-- ============================================================
--  VIEWS ÚTEIS
-- ============================================================

-- Produtos com stock abaixo do mínimo
CREATE OR REPLACE VIEW vw_stock_critico AS
SELECT
  p.id,
  p.nome_comercial,
  p.nome_generico,
  p.stock_atual,
  p.stock_minimo,
  (p.stock_minimo - p.stock_atual) AS quantidade_em_falta
FROM produtos p
WHERE p.stock_atual <= p.stock_minimo
  AND p.ativo = 1
ORDER BY quantidade_em_falta DESC;

-- Lotes próximos do vencimento (próximos 90 dias)
CREATE OR REPLACE VIEW vw_lotes_a_vencer AS
SELECT
  l.id,
  p.nome_comercial,
  l.numero_lote,
  l.validade,
  l.quantidade,
  DATEDIFF(l.validade, CURDATE()) AS dias_restantes
FROM lotes l
INNER JOIN produtos p ON p.id = l.produto_id
WHERE l.validade BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 90 DAY)
  AND l.quantidade > 0
ORDER BY l.validade;

-- Resumo de vendas diárias
CREATE OR REPLACE VIEW vw_vendas_diarias AS
SELECT
  DATE(v.data_venda)      AS data,
  COUNT(*)                AS total_vendas,
  SUM(v.total)            AS receita_total,
  SUM(v.desconto)         AS descontos_total,
  AVG(v.total)            AS ticket_medio
FROM vendas v
WHERE v.status = 'concluida'
GROUP BY DATE(v.data_venda)
ORDER BY data DESC;

-- ============================================================
--  DADOS INICIAIS (seed)
-- ============================================================

INSERT INTO categorias (nome, requer_receita, controlado) VALUES
  ('Analgésicos',         0, 0),
  ('Antibióticos',        1, 0),
  ('Anti-inflamatórios',  0, 0),
  ('Psicotrópicos',       1, 1),
  ('Vitaminas e Suplementos', 0, 0),
  ('Antihipertensivos',   1, 0),
  ('Antidiabéticos',      1, 0),
  ('Produtos de Higiene', 0, 0);

INSERT INTO funcionarios (nome, cargo, username, senha_hash) VALUES
  ('Administrador', 'admin', 'admin', '$2b$12$placeholder_trocar_na_primeira_entrada');

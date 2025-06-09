-- Inserindo médicos
INSERT INTO PESSOA (nome, cpf, telefone, email, idade, crm, tipo)VALUES ('Dr. João Silva', '11111111111', '99999-1111', 'joao.silva@hospital.com', 45, 'CRM-SP-12345', 'MEDICO');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, crm, tipo)VALUES ('Dra. Ana Souza', '22222222222', '99999-2222', 'ana.souza@hospital.com', 38, 'CRM-RJ-67890', 'MEDICO');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, crm, tipo)VALUES ('Dr. Paulo Mendes', '55555555555', '99999-5555', 'paulo.mendes@hospital.com', 50, 'CRM-MG-11223', 'MEDICO');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, crm, tipo)VALUES ('Dra. Beatriz Lima', '66666666666', '99999-6666', 'beatriz.lima@hospital.com', 41, 'CRM-SP-99887', 'MEDICO');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, crm, tipo)VALUES ('Dr. Renato Alves', '77777777777', '99999-7777', 'renato.alves@hospital.com', 39, 'CRM-RJ-33445', 'MEDICO');

-- Inserindo pacientes
INSERT INTO PESSOA (nome, cpf, telefone, email, idade, tipo)VALUES ('Carlos Pereira', '33333333333', '99999-3333', 'carlos.pereira@email.com', 29, 'PACIENTE');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, tipo)VALUES ('Maria Oliveira', '44444444444', '99999-4444', 'maria.oliveira@email.com', 34, 'PACIENTE');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, tipo)VALUES ('Lucas Fernandes', '88888888888', '99999-8888', 'lucas.fernandes@email.com', 22, 'PACIENTE');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, tipo)VALUES ('Juliana Costa', '99999999999', '99999-9999', 'juliana.costa@email.com', 31, 'PACIENTE');

INSERT INTO PESSOA (nome, cpf, telefone, email, idade, tipo)VALUES ('Roberto Silva', '00000000000', '99999-0000', 'roberto.silva@email.com', 47, 'PACIENTE');

-- Inserindo agendas

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T08:00:00', '2025-06-08T08:30:00', 'AGENDADO', 1);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T08:30:00', '2025-06-08T09:00:00', 'AGENDADO', 2);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T09:00:00', '2025-06-08T09:30:00', 'AGENDADO', 3);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T09:30:00', '2025-06-08T10:00:00', 'AGENDADO', 4);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T10:00:00', '2025-06-08T10:30:00', 'AGENDADO', 5);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T10:30:00', '2025-06-08T11:00:00', 'DISPONIVEL', 1);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T11:00:00', '2025-06-08T11:30:00', 'DISPONIVEL', 2);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T11:30:00', '2025-06-08T12:00:00', 'DISPONIVEL', 3);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T13:00:00', '2025-06-08T13:30:00', 'DISPONIVEL', 4);

INSERT INTO tb_agenda (data_hora_inicio, data_hora_fim, status, medico_id) VALUES ('2025-06-08T13:30:00', '2025-06-08T14:00:00', 'DISPONIVEL', 5);


-- Inserindo consultas
INSERT INTO tb_consulta (valor, observacao, paciente_id, agenda_id)VALUES (250.00, 'Consulta de rotina', 6, 1);
INSERT INTO tb_consulta (valor, observacao, paciente_id, agenda_id)VALUES (300.00, 'Retorno após exames', 7, 2);
INSERT INTO tb_consulta (valor, observacao, paciente_id, agenda_id)VALUES ( 200.00, 'Consulta sobre dores musculares', 6, 3);
INSERT INTO tb_consulta (valor, observacao, paciente_id, agenda_id)VALUES ( 280.00, 'Avaliação cardiológica', 8, 4);
INSERT INTO tb_consulta (valor, observacao, paciente_id, agenda_id)VALUES ( 180.00, 'Consulta de dermatologia', 9, 5);
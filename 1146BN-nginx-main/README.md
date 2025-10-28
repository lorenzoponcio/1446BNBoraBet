# Plataforma de Apostas — Monorepo (Spring Boot + Eureka + Gateway)

Este repositório foi adaptado do template original para o **contexto de casa de apostas**.

## Serviços
- **auth-service**: usuários e autenticação (JWT, RBAC: USER, SUPPORT, RISK_MANAGER, ADMIN, COMPLIANCE)
- **bets-service**: apostas e simulação (`/api/bets`)
- **history-service**: histórico e resultados (`/api/history`)
- **matches-service**: partidas e estatísticas (`/api/matches`)
- **tournaments-service**: campeonatos e times (`/api/tournaments`)
- **audit-service**: logs e auditoria (`/api/audit`)
- **service-discovery**: Eureka Server
- **gateway-service**: Spring Cloud Gateway com filtro de autorização

## Regras de Autorização iniciais (Gateway)
- `/api/bets/**` e `/api/history/**` → `USER` ou superior
- `/api/matches/admin/**` → `RISK_MANAGER` ou superior
- `/api/tournaments/admin/**` → `ADMIN` ou superior
- `/api/audit/**` → `COMPLIANCE`

> A hierarquia é: USER < SUPPORT < RISK_MANAGER < ADMIN < COMPLIANCE.

## Como executar
```bash
docker compose up --build
```
Acesse o Gateway em `http://localhost:8080` e o Eureka em `http://localhost:8083`.

## O que foi feito da atividade
- ✅ **Remoção** dos serviços `demo1` e `demo2`.
- ✅ **Inicialização** dos novos serviços via estrutura compatível com Spring Initializr (Web, Actuator, Eureka Client).
- ✅ **Ajuste de roles** no `auth-service` (`RoleType`) e **mapeamento de rotas x roles** no `gateway-service` (`AuthorizationFilter`).
- ✅ **Estrutura** similar ao modelo hexagonal (pacote `interfaces` + aplicação separada); você pode evoluir com `domain`, `application`, `infrastructure` conforme necessário.

## Próximos passos sugeridos
1. **Persistência**: adicionar banco (PostgreSQL) por serviço e repositórios.
2. **Eventos**: publicar/consumir eventos de apostas (Kafka/RabbitMQ) – p.ex. `bet.placed`, `bet.settled`.
3. **Compliance**: registrar ações sensíveis no `audit-service` + retenção e trilhas de auditoria.
4. **Validador de odds**: endpoints administrativos em `matches-service` para gestão de odds (role `RISK_MANAGER`).


# 二手书商城（Java + React）

这是一个可扩展的二手书商城基础实现：
- 后端：Spring Boot（分层 + 接口化服务，便于替换支付/物流实现）
- 前端：React + Vite
- 模块：登录注册、图书、订单、支付、物流

## 目录
- `backend`：Java 服务
- `frontend`：React 前端

## 本地启动

### 1) 后端
```bash
cd backend
mvn spring-boot:run
```

### 2) 前端
```bash
cd frontend
npm install
npm run dev
```

## 核心 API
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/books`
- `POST /api/orders`
- `POST /api/payments`
- `POST /api/logistics/ship`

## 测试
```bash
cd backend
mvn test
```

## 上线建议（生产）
- 接入 MySQL / PostgreSQL
- 密码改为 BCrypt 加密
- 使用 JWT + Spring Security
- 对接真实支付网关（如支付宝/微信/Stripe）
- 对接真实物流 API
- 增加库存锁、退款、售后、风控、审计日志
- 使用 Docker + Nginx + CI/CD（GitHub Actions）进行部署

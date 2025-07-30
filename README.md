# BITE-OJ 在线判题系统

<div align="center">
  <img src="img.png" alt="BITE-OJ Logo" width="200"/>
  
  一个基于微服务架构的在线判题系统
  
  ![Java](https://img.shields.io/badge/Java-17-orange)
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.1-brightgreen)
  ![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2022.0.0-blue)
  ![MySQL](https://img.shields.io/badge/MySQL-5.7-blue)
  ![Redis](https://img.shields.io/badge/Redis-latest-red)
  ![Docker](https://img.shields.io/badge/Docker-Supported-blue)
</div>

## 📖 项目简介

BITE-OJ 是一个功能完整的在线判题系统，采用 Spring Boot 3.x + Spring Cloud 微服务架构设计。系统支持多语言编程题目的在线判题，提供完整的竞赛管理、用户管理、题目管理等功能，适用于编程教学、算法竞赛等场景。

## 🏗️ 系统架构

### 微服务架构图

```
┌─────────────────┐    ┌─────────────────┐
│   C端前端界面    │    │   B端管理界面    │
│  (用户端/题目)   │    │  (管理员界面)    │
└─────────┬───────┘    └─────────┬───────┘
          │                      │
          └──────────┬───────────┘
                     │
              ┌──────▼──────┐
              │  Nginx      │
              │  负载均衡    │
              └──────┬──────┘
                     │
              ┌──────▼──────┐
              │ Gateway     │
              │ 微服务网关   │
              └──────┬──────┘
                     │
         ┌───────────┼───────────┐
         │           │           │
   ┌─────▼────┐ ┌───▼───┐ ┌─────▼────┐
   │ System   │ │Friend │ │  Judge   │
   │ 系统管理  │ │社交模块│ │ 判题服务  │
   └─────┬────┘ └───┬───┘ └─────┬────┘
         │          │           │
         └──────────┼───────────┘
                    │
             ┌──────▼──────┐
             │    Job      │
             │  定时任务    │
             └─────────────┘
```

## 🛠️ 技术栈

### 后端技术

| 技术栈 | 版本 | 说明 |
|--------|------|------|
| **Java** | 17 | 开发语言 |
| **Spring Boot** | 3.0.1 | 微服务框架 |
| **Spring Cloud** | 2022.0.0 | 微服务生态 |
| **Spring Cloud Alibaba** | 2022.0.0.0-RC2 | 阿里巴巴微服务组件 |
| **MyBatis-Plus** | 3.5.5 | ORM框架 |
| **MySQL** | 5.7 | 关系型数据库 |
| **Redis** | latest | 缓存数据库 |
| **RabbitMQ** | 3.8.30 | 消息队列 |
| **Elasticsearch** | 8.5.3 | 搜索引擎 |
| **Nacos** | 2.2.2 | 服务注册与配置中心 |
| **XXL-Job** | 2.4.0 | 分布式定时任务 |
| **Docker** | - | 容器化部署 |
| **Nginx** | 1.21 | 反向代理 |

### 开发工具与组件

| 组件 | 版本 | 说明 |
|------|------|------|
| **FastJSON** | 2.0.43 | JSON处理 |
| **JWT** | 0.9.1 | 身份认证 |
| **Hutool** | 5.8.22 | Java工具库 |
| **Lombok** | - | 代码生成 |
| **Swagger** | 2.2.0 | API文档 |
| **PageHelper** | 2.0.0 | 分页插件 |

## 📁 项目结构

```
bite-oj-master/
├── oj-api/                     # API接口定义模块
├── oj-common/                  # 公共模块
│   ├── oj-common-core/         # 核心公共组件
│   ├── oj-common-security/     # 安全认证组件
│   ├── oj-common-redis/        # Redis组件
│   ├── oj-common-mybatis/      # MyBatis组件
│   ├── oj-common-rabbitmq/     # RabbitMQ组件
│   ├── oj-common-file/         # 文件处理组件
│   ├── oj-common-elasticsearch/# Elasticsearch组件
│   ├── oj-common-message/      # 消息处理组件
│   └── oj-common-swagger/      # Swagger文档组件
├── oj-gateway/                 # 微服务网关
├── oj-modules/                 # 业务模块
│   ├── oj-system/              # 系统管理模块
│   ├── oj-friend/              # 社交功能模块
│   ├── oj-judge/               # 判题服务模块
│   └── oj-job/                 # 定时任务模块
└── deploy/                     # 部署配置
    ├── dev/                    # 开发环境
    └── test/                   # 测试环境
```

## 🚀 核心功能

### 1. 系统管理模块 (oj-system)
- **用户管理**: 用户注册、登录、信息管理、状态控制
- **管理员管理**: 管理员登录、权限管理、系统配置
- **题目管理**: 题目的增删改查、难度分级、用例管理
- **竞赛管理**: 竞赛创建、编辑、发布、参赛管理

### 2. 社交功能模块 (oj-friend)
- **消息系统**: 站内信、点对点通信、群发消息
- **用户交互**: 好友系统、排行榜、讨论区
- **竞赛报名**: 用户竞赛报名、成绩查询

### 3. 判题服务模块 (oj-judge)
- **代码执行**: 支持Java编程语言的在线执行
- **安全沙箱**: 基于Docker的代码执行环境，限制资源使用
- **结果判定**: 编译检查、运行时检查、答案比对
- **性能评估**: 时间复杂度、空间复杂度评估

### 4. 定时任务模块 (oj-job)
- **竞赛任务**: 自动开始/结束竞赛、排名统计
- **数据清理**: 定期清理临时文件、过期数据
- **统计分析**: 生成用户统计、系统报告

### 5. 微服务网关 (oj-gateway)
- **路由转发**: 统一入口，请求路由到对应服务
- **身份认证**: JWT token验证
- **限流熔断**: 系统保护机制

## 🔒 安全特性

### Docker 沙箱执行环境
- **资源限制**: 限制内存使用(100MB)、CPU使用(1核心)、执行时间(5秒)
- **权限控制**: 禁用网络访问、只读根文件系统
- **隔离执行**: 每次判题使用独立容器，避免代码间相互影响
- **自动清理**: 执行完成后自动删除容器和临时文件

### 身份认证与授权
- **JWT认证**: 基于JSON Web Token的无状态认证
- **角色权限**: 区分普通用户和管理员权限
- **接口保护**: 重要接口需要身份验证

## 📊 数据库设计

### 核心数据表

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| **tb_sys_user** | 管理员用户表 | user_id, user_account, password, nick_name |
| **tb_user** | 普通用户表 | user_id, phone, nick_name, school_name, status |
| **tb_question** | 题目表 | question_id, title, difficulty, content, time_limit |
| **tb_exam** | 竞赛表 | exam_id, title, start_time, end_time, status |
| **tb_exam_question** | 竞赛题目关系表 | exam_question_id, exam_id, question_id, question_order |
| **tb_user_exam** | 用户竞赛关系表 | user_exam_id, user_id, exam_id, score, exam_rank |
| **tb_user_submit** | 用户提交记录表 | submit_id, user_id, question_id, user_code, pass, score |
| **tb_message** | 消息表 | message_id, send_id, rec_id, text_id |

## 🐳 部署说明

### 环境要求
- **Java**: 17+
- **Maven**: 3.6+
- **Docker**: 20.0+
- **Docker Compose**: 1.29+

### Mac M1/ARM64适配
本项目已完全适配Apple M1芯片，无需额外配置。系统会自动选择对应的本地库：
- 使用Netty作为Docker传输层，避免JAX-RS兼容性问题
- 自动加载ARM64版本的本地库
- 推荐使用Unix Socket连接Docker（`unix:///var/run/docker.sock`）

### 快速启动

1. **克隆项目**
```bash
git clone <repository-url>
cd bite-oj-master
```

2. **构建项目**
```bash
mvn clean package -DskipTests
```

3. **启动服务**
```bash
cd deploy/test
docker-compose up -d
```

### 服务端口

| 服务 | 端口 | 说明 |
|------|------|------|
| **MySQL** | 3306 | 数据库服务 |
| **Redis** | 6379 | 缓存服务 |
| **Nacos** | 8848 | 服务注册中心 |
| **RabbitMQ** | 5672/15672 | 消息队列/管理界面 |
| **Elasticsearch** | 9200 | 搜索服务 |
| **Kibana** | 5601 | ES可视化界面 |
| **XXL-Job** | 8080 | 任务调度中心 |
| **Gateway** | 19090 | 微服务网关 |
| **System** | 9201 | 系统管理服务 |
| **Friend** | 9202 | 社交功能服务 |
| **Judge** | 9204 | 判题服务 |
| **Job** | 9203 | 定时任务服务 |
| **Nginx** | 80/10030 | Web服务器 |

### 前端访问
- **用户端**: http://localhost (端口80)
- **管理端**: http://localhost:10030

## 🔧 配置说明

### Nacos配置中心
系统使用Nacos作为配置中心，主要配置包括：
- 数据库连接配置
- Redis连接配置
- RabbitMQ连接配置
- Elasticsearch连接配置
- **JWT密钥配置（必需）**

### 必需的JWT配置
```yaml
jwt:
  secret: bite-oj-jwt-secret-key-2023  # 必须配置
  expiration: 86400                    # token过期时间(秒)
```

### Docker沙箱配置
```yaml
# 沙箱资源限制配置
sandbox:
  docker:
    host: unix:///var/run/docker.sock  # Mac/Linux推荐
    # host: tcp://localhost:2375       # Windows可选
  limit:
    memory: 100000000      # 内存限制 100MB
    memory-swap: 100000000 # 交换内存限制
    cpu: 1                 # CPU核心数限制
    time: 5                # 执行时间限制(秒)
```

## ❓ 常见问题

### 编译问题
- **Lombok枚举问题**: 如遇到枚举构造器相关编译错误，检查Lombok版本兼容性
- **JAX-RS冲突**: 项目已解决Spring Boot 3.x的Jakarta命名空间兼容问题

### Docker连接问题
- **Mac用户**: 推荐使用`unix:///var/run/docker.sock`
- **Windows用户**: 需要启用Docker Desktop的TCP端口2375
- **连接被拒绝**: 检查Docker Desktop是否正常运行

### 配置问题
- **JWT配置缺失**: 必须在Nacos中配置`jwt.secret`
- **数据库连接**: 确保MySQL服务正常启动并可访问

## 🎯 系统特点

1. **微服务架构**: 模块化设计，易于维护和扩展
2. **安全可靠**: Docker沙箱执行，多重安全防护
3. **高性能**: Redis缓存、连接池、异步处理
4. **易部署**: Docker容器化，一键部署
5. **功能完整**: 涵盖OJ系统的所有核心功能
6. **技术先进**: 采用最新的Spring Boot 3.x技术栈

## 📈 性能优化

- **缓存策略**: Redis缓存热点数据，提升访问速度
- **消息队列**: 异步处理判题任务，提高系统吞吐量
- **连接池**: 数据库连接池优化，减少连接开销
- **索引优化**: Elasticsearch全文搜索，快速定位题目
- **容器池**: Docker容器池复用，减少容器创建开销

## 🤝 贡献指南

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 📞 联系方式

如有问题或建议，请通过以下方式联系：

- 项目Issues: [GitHub Issues](https://github.com/your-repo/issues)
- 邮箱: your-email@example.com

---

⭐ 如果这个项目对您有帮助，请给个星标支持一下！

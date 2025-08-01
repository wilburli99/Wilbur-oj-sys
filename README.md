# Wilbur-OJ 在线判题系统

## 📖 项目简介

Wilbur-OJ 是一个功能完整的在线判题系统，采用 Spring Boot 3.x + Spring Cloud 微服务架构设计。系统支持多语言编程题目的在线判题，提供完整的竞赛管理、用户管理、题目管理等功能，适用于编程教学、算法竞赛等场景。

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
   │ 系统管理  │ │用户模块│ │ 判题服务  │
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

### 前端技术

| 技术栈 | 版本 | 说明 |
|--------|------|------|
| **Vue** | 3.4.x | 前端框架 |
| **Element Plus** | 2.7.x | UI组件库 |
| **Vite** | 5.x | 构建工具 |
| **Vue Router** | 4.x | 路由管理 |
| **Axios** | 1.7.x | HTTP客户端 |
| **Ace Editor** | 1.4.x | 代码编辑器 |
| **Vue Quill** | 1.2.x | 富文本编辑器 |

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
│   ├── oj-friend/              # 用户功能模块
│   ├── oj-judge/               # 判题服务模块
│   └── oj-job/                 # 定时任务模块
├── frontend/                   # 前端项目目录
│   ├── admin/oj-fe-b-master/   # B端管理前端 (Vue3 + Element Plus)
│   │   ├── src/                # 源代码目录
│   │   ├── package.json        # 项目依赖
│   │   └── vite.config.js      # Vite配置
│   └── user/oj-fe-c-master/    # C端用户前端 (Vue3 + Element Plus)
│       ├── src/                # 源代码目录
│       ├── package.json        # 项目依赖
│       └── vite.config.js      # Vite配置
└── deploy/                     # 部署配置
    ├── dev/                    # 开发环境
    └── test/                   # 测试环境
```

## 🚀 核心功能

### 前端集成
项目支持前后端分离架构，前端项目可以独立开发和部署：

#### 前端项目结构
```
frontend/
├── admin/          # B端管理前端项目
│   ├── package.json
│   ├── src/
│   └── ...
└── user/           # C端用户前端项目
    ├── package.json
    ├── src/
    └── ...
```

#### 前端集成步骤
1. **前端项目已集成**：B端和C端前端项目已集成到 `frontend/admin/oj-fe-b-master/` 和 `frontend/user/oj-fe-c-master/` 目录
2. **API配置已就绪**：前端项目已配置代理，API请求自动转发到后端网关 `http://localhost:19090`
3. **独立运行**：每个前端项目都可以独立运行和开发
4. **构建部署**：构建后的静态文件可以部署到Nginx或CDN

#### 开发环境运行
```bash
# B端管理前端
cd frontend/admin/oj-fe-b-master
npm install
npm run dev

# C端用户前端
cd frontend/user/oj-fe-c-master
npm install
npm run dev
```

#### 生产环境部署
```bash
# 构建前端项目
cd frontend/admin/oj-fe-b-master && npm run build
cd frontend/user/oj-fe-c-master && npm run build

# 部署到Nginx
# 参考 deploy/test/nginx/conf/frontend.conf 配置
```

#### 前端功能特性

**B端管理前端功能**：
- ✅ 用户管理：用户列表、添加、编辑、删除
- ✅ 题目管理：题目CRUD、测试用例管理
- ✅ 竞赛管理：竞赛创建、编辑、发布
- ✅ 系统监控：数据统计、日志查看
- ✅ 权限管理：角色分配、权限控制

**C端用户前端功能**：
- ✅ 用户注册/登录：账号管理
- ✅ 题目练习：在线编程、代码提交
- ✅ 竞赛参与：报名、答题、排名
- ✅ 个人中心：个人信息、提交记录
- ✅ 排行榜：用户排名、成绩统计

### 1. 系统管理模块 (oj-system)
- **用户管理**: 用户注册、登录、信息管理、状态控制
- **管理员管理**: 管理员登录、权限管理、系统配置
- **题目管理**: 题目的增删改查、难度分级、用例管理
- **竞赛管理**: 竞赛创建、编辑、发布、参赛管理

### 2. 用户功能模块 (oj-friend)
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
- **Node.js**: 16.0+
- **npm**: 8.0+
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

2. **构建后端项目**
```bash
mvn clean package -DskipTests
```

3. **启动后端服务**
```bash
cd deploy/test
docker-compose up -d
```

4. **启动前端项目（可选）**
```bash
# B端管理前端
cd frontend/admin/oj-fe-b-master
npm install
npm run dev

# C端用户前端（新开终端）
cd frontend/user/oj-fe-c-master
npm install
npm run dev
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
| **Admin Frontend** | 3000 | B端管理前端 |
| **User Frontend** | 3001 | C端用户前端 |


## 🔧 配置说明
### Nacos配置中心
系统使用Nacos作为配置中心，主要配置包括：
- 数据库连接配置
- Redis连接配置
- RabbitMQ连接配置
- Elasticsearch连接配置
- JWT密钥配置（必需）

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

## 🎯 系统特点

1. **微服务架构**: 模块化设计，易于维护和扩展
2. **前后端分离**: 支持独立开发和部署
3. **安全可靠**: Docker沙箱执行，多重安全防护
4. **高性能**: Redis缓存、连接池、异步处理
5. **易部署**: Docker容器化，一键部署
6. **功能完整**: 涵盖OJ系统的所有核心功能
7. **技术先进**: 采用最新的Spring Boot 3.x和Vue 3技术栈

## 📈 性能优化

- **缓存策略**: Redis缓存热点数据，提升访问速度
- **消息队列**: 异步处理判题任务，提高系统吞吐量
- **连接池**: 数据库连接池优化，减少连接开销
- **索引优化**: Elasticsearch全文搜索，快速定位题目
- **容器池**: Docker容器池复用，减少容器创建开销
- **前端优化**: Vite构建优化，代码分割和懒加载

## 🚀 开发指南

### 后端开发
```bash
# 启动后端服务
cd deploy/test
docker-compose up -d

# 查看服务日志
docker-compose logs -f [service-name]
```

### 前端开发
```bash
# B端管理前端开发
cd frontend/admin/oj-fe-b-master
npm install
npm run dev

# C端用户前端开发
cd frontend/user/oj-fe-c-master
npm install
npm run dev
```

### API文档
- **Swagger UI**: http://localhost:19090/swagger-ui.html
- **后端网关**: http://localhost:19090
- **前端代理**: 开发时通过Vite代理到后端

### 调试技巧
1. **后端调试**: 使用IDE断点调试，查看Docker容器日志
2. **前端调试**: 使用Vue DevTools，查看Network面板
3. **数据库调试**: 使用MySQL客户端连接查看数据
4. **缓存调试**: 使用Redis客户端查看缓存数据

---

⭐ 如果这个项目对您有帮助，请给个星标支持一下！

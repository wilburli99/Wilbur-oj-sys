# Wilbur-OJ 前端项目

## 📖 项目简介

本项目包含Wilbur-OJ在线判题系统的两个前端项目：
- **B端管理前端**：管理员界面，用于系统管理、题目管理、竞赛管理等
- **C端用户前端**：用户界面，用于题目练习、竞赛参与、个人中心等

## 🏗️ 项目架构

### 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| **Vue** | 3.4.x | 前端框架 |
| **Element Plus** | 2.7.x | UI组件库 |
| **Vite** | 5.x | 构建工具 |
| **Vue Router** | 4.x | 路由管理 |
| **Axios** | 1.7.x | HTTP客户端 |
| **Ace Editor** | 1.4.x | 代码编辑器 |
| **Vue Quill** | 1.2.x | 富文本编辑器 |

### 目录结构

```
frontend/
├── admin/oj-fe-b-master/          # B端管理前端项目
│   ├── src/
│   │   ├── components/            # 组件目录
│   │   ├── views/                 # 页面目录
│   │   ├── router/                # 路由配置
│   │   ├── utils/                 # 工具函数
│   │   └── main.js                # 入口文件
│   ├── package.json               # 项目依赖
│   ├── vite.config.js             # Vite配置
│   └── index.html                 # HTML模板
└── user/oj-fe-c-master/           # C端用户前端项目
    ├── src/
    │   ├── components/            # 组件目录
    │   ├── views/                 # 页面目录
    │   ├── router/                # 路由配置
    │   ├── utils/                 # 工具函数
    │   └── main.js                # 入口文件
    ├── package.json               # 项目依赖
    ├── vite.config.js             # Vite配置
    └── index.html                 # HTML模板
```

## 🚀 快速开始

### 环境要求

- **Node.js**: 16.0+
- **npm**: 8.0+
- **后端服务**: 确保后端微服务已启动

### 1. 安装依赖

```bash
# B端管理前端
cd frontend/admin/oj-fe-b-master
npm install

# C端用户前端
cd frontend/user/oj-fe-c-master
npm install
```

### 2. 启动开发服务器

```bash
# B端管理前端 (终端1)
cd frontend/admin/oj-fe-b-master
npm run dev

# C端用户前端 (终端2)
cd frontend/user/oj-fe-c-master
npm run dev
```

### 3. 访问应用

- **B端管理界面**: http://localhost:5173 (或Vite分配的端口)
- **C端用户界面**: http://localhost:5174 (或Vite分配的端口)

## 🔧 项目配置

### API配置

两个前端项目都配置了代理，将API请求转发到后端网关：

#### B端管理前端 (oj-fe-b-master)
```javascript
// vite.config.js
server: {
  proxy: {
    "/dev-api": {
      target: "http://127.0.0.1:19090/system",
      rewrite: (p) => p.replace(/^\/dev-api/, ""),
    },
  },
}
```

#### C端用户前端 (oj-fe-c-master)
```javascript
// vite.config.js
server: {
  proxy: {
    "/dev-api": {
      target: "http://127.0.0.1:19090/friend",
      rewrite: (p) => p.replace(/^\/dev-api/, ""),
    },
  },
}
```

### 请求配置

两个项目都使用统一的请求配置：

```javascript
// utils/request.js
const service = axios.create({
  baseURL: "/dev-api",
  timeout: 5000,
})

// 请求拦截器 - 自动添加Token
service.interceptors.request.use((config) => {
  if (getToken()) {
    config.headers["Authorization"] = "Bearer " + getToken();
  }
  return config;
})

// 响应拦截器 - 统一错误处理
service.interceptors.response.use((res) => {
  const code = res.data.code;
  const msg = res.data.msg;
  if (code === 3001) {
    // Token过期，跳转登录
    removeToken()
    router.push('/oj/login')
    return Promise.reject(new Error(msg));
  } else if (code !== 1000) {
    ElMessage.error(msg);
    return Promise.reject(new Error(msg));
  } else {
    return Promise.resolve(res.data);
  }
})
```

## 📦 构建部署

### 开发环境构建

```bash
# B端构建
cd frontend/admin/oj-fe-b-master
npm run build

# C端构建
cd frontend/user/oj-fe-c-master
npm run build
```

### 生产环境部署

#### 方案一：Nginx部署

1. **构建项目**
```bash
# 构建两个前端项目
cd frontend/admin/oj-fe-b-master && npm run build
cd frontend/user/oj-fe-c-master && npm run build
```

2. **Nginx配置**
```nginx
# 参考 deploy/test/nginx/conf/frontend.conf
# B端管理界面
location /admin {
    alias /app/frontend/admin/dist;
    try_files $uri $uri/ /admin/index.html;
}

# C端用户界面
location /user {
    alias /app/frontend/user/dist;
    try_files $uri $uri/ /user/index.html;
}

# API代理
location /api/ {
    proxy_pass http://gateway:19090/;
    proxy_set_header Host $host;
    proxy_set_header X-Real-IP $remote_addr;
}
```

#### 方案二：Docker部署

```dockerfile
# B端Dockerfile示例
FROM nginx:alpine
COPY dist/ /usr/share/nginx/html/admin/
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
```

## 🔍 功能特性

### B端管理前端功能
- ✅ 用户管理：用户列表、添加、编辑、删除
- ✅ 题目管理：题目CRUD、测试用例管理
- ✅ 竞赛管理：竞赛创建、编辑、发布
- ✅ 系统监控：数据统计、日志查看
- ✅ 权限管理：角色分配、权限控制

### C端用户前端功能
- ✅ 用户注册/登录：账号管理
- ✅ 题目练习：在线编程、代码提交
- ✅ 竞赛参与：报名、答题、排名
- ✅ 个人中心：个人信息、提交记录
- ✅ 排行榜：用户排名、成绩统计

## 🛠️ 开发指南

### 项目结构说明

```
src/
├── components/          # 公共组件
│   ├── Header.vue      # 头部组件
│   ├── Sidebar.vue     # 侧边栏组件
│   └── ...
├── views/              # 页面组件
│   ├── Login.vue       # 登录页面
│   ├── Dashboard.vue   # 仪表板
│   └── ...
├── router/             # 路由配置
│   └── index.js        # 路由定义
├── utils/              # 工具函数
│   ├── request.js      # HTTP请求封装
│   ├── cookie.js       # Cookie操作
│   └── ...
├── assets/             # 静态资源
│   ├── images/         # 图片资源
│   └── styles/         # 样式文件
└── main.js             # 应用入口
```

### 开发规范

1. **组件命名**：使用PascalCase，如`UserList.vue`
2. **文件命名**：使用kebab-case，如`user-list.vue`
3. **API调用**：统一使用`utils/request.js`中的封装
4. **状态管理**：使用Vue3的Composition API
5. **样式规范**：使用SCSS，遵循BEM命名规范

### 常用命令

```bash
# 开发
npm run dev

# 构建
npm run build

# 预览构建结果
npm run preview

# 代码检查
npm run lint

# 类型检查
npm run type-check
```

## 🔧 故障排除

### 常见问题

1. **端口冲突**
   ```bash
   # 修改vite.config.js中的端口配置
   server: {
     port: 3000  // 自定义端口
   }
   ```

2. **API请求失败**
   - 检查后端服务是否启动
   - 确认代理配置是否正确
   - 检查网络连接

3. **跨域问题**
   - 确认后端网关CORS配置
   - 检查代理配置
   - 使用浏览器开发者工具查看网络请求

4. **Token过期**
   - 自动跳转到登录页面
   - 清除本地存储的Token
   - 重新登录获取新Token

### 调试技巧

1. **Vue DevTools**：安装Vue DevTools浏览器扩展
2. **Network面板**：查看API请求和响应
3. **Console面板**：查看错误信息和调试日志
4. **Sources面板**：断点调试JavaScript代码

⭐ 如果这个项目对您有帮助，请给个星标支持一下！ 
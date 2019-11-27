### 工作经历
   - 溢唐科技数据有限公司(Yitang Data) / 软件工程师 / 2018年6月 - 现在
      - 负责自动量化交易策略开发和平台实现
      - 区块链钱包先关服务开发
      - 实现交易所OpenAPI服务, 实名认证服务
      - 优化交易所大量流水数据存储
      - 负责建模语言运行时和工具维护开发

   - 友励科学软件(上海)有限公司 (Uniquesoft) / 软件工程师 / 2011年8月 - 2018年6月
      - 开发基于Eclipse的建模领域特定语言(DSL)工具 
      - 重构基于web的代码分析工具, 改进代码度量查询 
      - 验证集成Java转PL/SQL工程, 在美国总部生产环境支持
      - 完成10+自动现代化迁移工程概念论证(POC)项目原型和验证 
      - 负责信息系统基础架构代码维护和开发, 工具链开发, 界面开发

### 教育
   - 本科 信息工程 /  华东理工大学 / 2007 - 2011
   - 本科 英语(辅修) / 华东理工大学 / 2007 - 2011

### 技术经历 

   - Yitang 业务项目

      - 实现实名认证openAPI服务, 
      - 为交易平台重构历史流水数据存储, 分表和并创建统一化查询接口, 自动生成不同资产交易流水的保存和查询代码
      - 实现交易所第三方OpenAPI, 通过商户秘钥和商户ID来认证(参考了其他主流交易所平台)
      - 实现了多种自动交易策略和web控制平台(Nodejs, Epxress, React)
      - 实现区块链钱包服务UTXO缓存插件, 支持快速UTXO查询
      - 学习智能合约smart contracts (ETH, HyperLedger)
      - 使用docker, portainer, k8s部署开发的服务

   - Yitang 工具

      - 为公司建模语言实现新的语言特性: EDL (entity deifinition language targeting Spring and Hiberante) 和 UIDL (UI definition language targeting GWT)
         - 实现更简化的UI组件定义语法并支持更方便的组件组合
         - 为状态机迁移增加Spring Cloud Message 通知机制
         - 升级EDL and UIDL建模工具, 支持命令行持续集成
         - 维护工具基础runtime代码


   - Uniquesoft工具

      - 基于Eclipse的建模工具套件
        - 基于Eclipse的代码编辑工具开发和优化: 代码高亮,语法语义检查, 代码outline
        - 集成后台代码生成器
        - 偏好设置界面, 比如代码检查等级
        - 性能优化
        - 设计并开发UML图形编辑器(基于Eclipse GMF), 包括类图和状态机图的编辑, 并和代码编辑保持双向同步
        - 需求文档编写, 和CTO进行语言特性讨论确认
        - 编写界面单元测试

      - 基于Web的代码分析工具
        - 实现新架构原型: web界面, NodeJS服务器, RabbitMQ, 已有代码分析器集成(Lisp)
        - 优化代码度量查询: 前端(Javascript, Nodejs, MongoDB)后端(Common Lisp)分离, 支持快速查询和树状数据查询
        - 实现浏览器代码编辑器语法和语义减产 (JISON, codemirror)

      - 信息系统
        - 实现基于bootstrap的GWT新主题, 实现新的界面组件包括时间线, 大屏滚动表格
        - 实现监管系统食品分类信息解析器(clojure instaparser), 并导入大量已有分类数据
        - 维护基础设施代码 (Spring, Hiberanate, GWT UI)         

   - Uniquesoft 存余代码自动重构

      - 云平台迁移
        - 实现Java AWS S3 IO API 替代代码里的本地File API, 支持工具自动重构并部署OpenAM项目到Heroku 

      - Java转PL/SQL迁移
        - 集成并修复从Java自动生成的PL/SQL代码, 向代码生成器团队报bug
        - 在美国总部进行生产环境部署和支持, 保证上海团队集成和美国部署进度一致, AT&T客户支持

      - MainFrame到Java/C# migration:
        - 使用主流技术(Spring, Hiberanate and Angular UI)实现项目原型, 并和从COBOL转成的Java代码集成
        - 创建自动化单元测试和集成测试 tests and integarion tests (robot) to verify the funtionality

      - Java 业务规则提取 
        - 实现基于drools规则引擎的业务规则原型
        - 创建单元测试(PowerMock)验证原始Java代码实现和规则引擎实现


### 语言和技术
   - Java: Spring, Hiberante, 
   - Distributed application: RabbitMq, Spring-cloud, Docker
   - Blockchain: bitcore
   - IDE development: Xtext, Eclipse Plugin development
   - Node: Typescript, Javascript, Rxjs, Express
   - Frontend: React, React Native, Angular, Ionic, GWT, bootstrap, material design, semantic UI
   - DB: MySQL, MongoDB
   - Lisp
   - COBOL

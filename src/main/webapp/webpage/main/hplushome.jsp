<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--360浏览器优先以webkit内核解析-->


    <title>qlong-iot开发平台</title>

    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="plug-in-ui/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in-ui/hplus/css/style.css?v=4.1.0" rel="stylesheet">


</head>

<body class="gray-bg">
<div class="row  border-bottom white-bg dashboard-header">
    <div class="col-sm-6">
        <h2>乾龙云</h2>
        <small>乾龙云平台框架图</small>
        <br>
        <br>
        <img src="plug-in/login/images/iot.png" width="100%" style="max-width:800px;">


    </div>

    <div class="col-sm-6">
        <blockquote class="text-warning" style="font-size:20px">
            1.1、	总体概述
            <br>1.1.1、基础模块说明
            <br>乾龙云平台基础模块设计理论是让开发变的更简单，使得程序员更加关注核心业务开发，而非大量时间花在简单增删改查业务上，并且让开发变的简单起来。
            <br>目前实现两种方式开发
            <br>1）	面向程序员的代码生成器
            <br>2）	面向web前端人员的代码生成器
            <br>面向非程序员，但是需要有定义的设计概念
            <br>生成所需要的全部基础代码，可以根据自己需要来修改。文件的导入导出代码因为涉及到数据校验环节，故而没有抽象为基础方法。需要自己按需实现
            <br>1.1.2、核心模块说明
            <br>乾龙云平台核心在于流程引擎调度一切可调度资源按照配置流程执行，并集成规则引擎，将业务逻辑开发变的更灵活。
            <br>平台分为三大部分
            <br>1）	认证接入
            <br>外部系统使用平台前的认证接入管理
            <br>2）	集成调度中心
            <br>认证通过后触发对应规则引发调度
            <br>3）	资源中心
            <br>属于可调度资源管理。
            <br>资源可以是信息流、设备、人、其他可接入系统
            <h3 class="text-danger">乾龙云平台</h3>
        </blockquote>

    </div>
    <div class="col-sm-12">
        <hr>
    </div>

</div>


<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h2>微信服务号</h2>
                </div>
                <div class="ibox-content">
                    <p>微信扫描二维码关注乾龙服务号：</p>
                    <p><img src="plug-in/login/images/qrcode_for_qlong.jpg" width="100%" style="max-width:264px;"></p>

                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h2>
                        乾龙云版本说明
                    </h2>
                </div>
                <div class="ibox-content">
                    <p>乾龙云是基于JEECG v3.6.6框架快速开发平台</p>
                    <p>
                        <span class="label label-warning">开源     &nbsp; | &nbsp; 免费  | &nbsp; 更多插件</span>
                    </p>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h2>Jeecg具有以下特点：</h2>
                </div>
                <div class="ibox-content">
                    <ol>
                        <li>采用主流J2EE框架，容易上手;</li>
                        <li>强大的代码生成器，一键生成</li>
                        <li>提供5套不同风格首页</li>
                        <li>开发效率很高，节省80%重复工作</li>
                        <li>使用最流行的的扁平化设计</li>
                        <li>在线开发能力，通过在线配置实现功能，零代码</li>
                        <li>在线报表配置能力，一次配置七种报表风格，支持移动报表</li>
                        <li>移动平台支持，采用Bootstrap技术，移动OA，移动报表</li>
                        <li>强大数据权限，访问级，按钮级、数据行级，列级，字段级</li>
                        <li>国际化能力，支持多语言</li>
                        <li>多数据源，跨数据源操作，便捷集成第三方系统</li>
                        <li>简易Excel、Word 导入导出,满足企业需求</li>
                        <li>插件开发，可插拔开发模式，集成第三方组件</li>
                        <li>流程定义，在线画流程，流程挂表单，符合国情流程</li>
                        <li>自定义表单，可视化拖拽布局，自定义表单风格</li>
                        <li>更多……</li>
                    </ol>
                </div>
            </div>
        </div>

    </div>
</div>


<!-- 全局js -->
<script src="plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="plug-in-ui/hplus/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="plug-in-ui/hplus/js/content.js"></script>
</body>

</html>

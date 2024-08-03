# 启动

## 修改application.yaml
```
wechat:
  appid: ${APPID:修改为appid}
  secret: ${SECRET:修改为secert}
```

## 本地启动
```bash
mvn spring-boot:run
```

## docker启动
```bash
docker build -t test ./
docker run -p 8080:8080 -d -e APPID="填写小程序appid" SECERT="填写小程序secert" test
```

# 测试接口

```
POST http://localhost:8080/wxLogin
Content-Type: application/json

{
  "jsCode": "随意填写，也可以是小程序登录jscode"
}
```
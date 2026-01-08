# ERNB
# Sentiment API on Kubernetes (Demo Project)

Простое Java/Spring Boot приложение для анализа тональности текста,
контейнеризованное в Docker и развёрнутое в Kubernetes (Minikube) с мониторингом через Prometheus + Grafana.
Ответ строится не на if/else, а на компактной логистической модели (см. `src/main/resources/model/sentiment-model.json`),
которая загружается сервисом при старте и возвращает вероятностную оценку.

## Локальный запуск без Kubernetes

```bash
mvn spring-boot:run
# или
mvn clean package -DskipTests
java -jar target/sentiment-0.0.1-SNAPSHOT.jar
```

Запрос с уверенностью модели:

```bash
curl "http://localhost:8080/api/sentiment?text=I%20love%20Kubernetes"
# => {"sentiment":"positive","confidence":0.89}
```

Проверка:

```bash
curl "http://localhost:8080/api/sentiment?text=I%20love%20Kubernetes"
```

## Docker

```bash
docker build -t sentiment-app:1.0.0 .
docker run --rm -p 8080:8080 sentiment-app:1.0.0
```

## Kubernetes (Minikube)

```bash
# если используете драйвер docker внутри minikube
eval $(minikube docker-env)
docker build -t sentiment-app:1.0.0 .

kubectl apply -f k8s/k8s.yaml
kubectl get pods
```

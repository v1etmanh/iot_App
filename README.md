Smart Door IoT System — Hệ thống cửa thông minh (ESP32 + Firebase + MQTT)

Mô tả ngắn
Hệ thống IoT điều khiển cửa thông minh qua ứng dụng di động/web với sensor, ESP32, Firebase cho auth/DB và MQTT cho realtime control.

Stack

Device: ESP32 (C/C++ / Arduino framework)

Cloud: Firebase (Realtime DB / Firestore / Auth / Hosting) hoặc MQTT Broker (Mosquitto / HiveMQ)

Mobile/Web: React Native / React

Protocols: MQTT, HTTPS

Tính năng chính

Mở/khóa cửa từ app (MQTT publish)

Cảm biến (magnetic switch, PIR, temp) báo trạng thái, gửi lên cloud

Quản lý user & quyền (Firebase Auth)

Lưu logs (who opened, when)

Remote access: Firebase Functions hoặc MQTT bridge

Phần firmware (ESP32)

Thư viện: PubSubClient (MQTT) hoặc Firebase ESP Client

Config WiFi & MQTT credentials (mã hoá / secure storage)

Nút emergency

OTA update (tùy)

Ví dụ cấu hình MQTT trên ESP

const char* ssid = "YOUR_SSID";
const char* password = "YOUR_PASS";
const char* mqtt_server = "mqtt.yourbroker.com";
const int mqtt_port = 1883;
const char* mqtt_user = "user";
const char* mqtt_pass = "pass";


Cài đặt & chạy

Flash firmware lên ESP32 (PlatformIO / Arduino IDE)

Deploy backend / Firebase rules

Cài app React/React Native và kết nối endpoint / MQTT broker

Biến môi trường / config

MQTT_BROKER_URL, MQTT_USERNAME, MQTT_PASSWORD

FIREBASE_API_KEY, FIREBASE_PROJECT_ID

OTA_ENDPOINT (nếu có)

Bảo mật

Dùng TLS cho MQTT (MQTT over TLS)

Xác thực người dùng bằng Firebase Auth

Mã hoá lưu trữ credentials trên thiết bị

Deployment

MQTT Broker: cloud (AWS IoT Core / HiveMQ / Mosquitto on VPS)

Firebase: deploy rules & functions

Firmware: quản lý OTA release

License & Links

License: MIT

Repo: https://github.com/USERNAME/smart-door-iot-system

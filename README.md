# Paydi_MMS_service

# Docker
# -- build APP
cd Paydi_MMS_service
mvn clean package
docker build -t paydi-mms-services:1.0.0 .
docker run -d --env-file app.env -p 8081:81 -t paydi-mms-services:1.0.0

#check
docker ps -a

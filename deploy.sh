echo "Deploying changes..."
# Pull changes from the live branch
git pull

# Build jar
sudo ./mvnw package 
#&& java -jar target/postgre-0.0.1-SNAPSHOT.jar

# Build the image with the new changes
sudo docker build . -t spring-postgre

# Shut down the existing containers
sudo docker-compose down

# Start the new containers
sudo docker-compose up -d
echo "Deployed!"
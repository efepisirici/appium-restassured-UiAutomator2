# Use a base image with Node.js since Appium is a Node.js server
FROM node:14

# Set environment variables for the Appium server
ENV APPIUM_VERSION=1.20.2
ENV NPM_CONFIG_PREFIX=/home/node/.npm-global

# Set the path to include the global npm bin directory
ENV PATH=$PATH:/home/node/.npm-global/bin

# Install Appium as user node
USER node
RUN npm install -g appium@${APPIUM_VERSION}

# Expose the default Appium port
EXPOSE 4723

# Set the entrypoint for the Appium server
ENTRYPOINT ["appium"]
CMD ["--allow-insecure", "chromedriver_autodownload"]

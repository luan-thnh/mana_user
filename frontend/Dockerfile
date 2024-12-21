FROM node:20

# Tạo thư mục làm việc
WORKDIR /app

# Copy file package
COPY package*.json ./

# Cài đặt dependencies
RUN npm install

# Copy toàn bộ mã nguồn
COPY . .

# Generate type
RUN npm run build

# Expose port
EXPOSE 3000

# Chạy ứng dụng
CMD ["npm", "start"]
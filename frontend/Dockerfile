# Base stage for dependencies
FROM node:20 AS deps
WORKDIR /app
COPY package*.json ./
RUN npm install --frozen-lockfile

# Build stage
FROM node:20 AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY . .
RUN npm run build

# Runtime stage
FROM node:20 AS runtime
WORKDIR /app
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public
COPY --from=builder /app/package*.json ./
RUN npm install --production --frozen-lockfile
EXPOSE 3000
CMD ["npm", "start"]

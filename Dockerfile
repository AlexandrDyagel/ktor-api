# Используем базовый образ с поддержкой JDK 17 и Gradle
FROM gradle:8.4.0-jdk21 AS build

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем исходный код проекта в контейнер
COPY --chown=gradle:gradle . /app

# Открываем порты
# Порт для Ktor
# Порт для отладки
EXPOSE 8080
EXPOSE 5005

ENV JAVA_TOOL_OPTIONS: "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

# Устанавливаем зависимости (это ускорит сборку)
RUN gradle build --no-daemon || return 0

# Запускаем приложение с отслеживанием изменений (флаг -t для непрерывного запуска)
CMD ["gradle", "-t", "run"]
# Food Voting App

This Spring Boot app was built as a simple replacement for the missing original Foodvoting app. It directly satisfies Prof. Park's assignment: **Add Winner Food in Foodvoting App**.

Users can add foods, vote for foods, and see the current winner food. If multiple foods have the same highest vote count, the app shows all tied winners.

## Features

- Add food with name, description, and optional image URL
- Vote for food
- Show foods sorted by vote count
- Show winner food on the home page
- Show winner details at `/winner`
- Show "No winner yet" when there are no votes
- Store data in MySQL when database variables are configured
- Fall back to a small H2 file database when Railway MySQL is unavailable, so the app still works for demo/submission

## Local MySQL

Create the database:

```sql
CREATE DATABASE foodvoting;
```

Default local settings:

```text
DB_URL=jdbc:mysql://localhost:3306/foodvoting
DB_USER=root
DB_PASSWORD=
```

Run with a password:

```bash
DB_PASSWORD=your_password ./gradlew bootRun
```

## Railway MySQL / Render

Set these environment variables on Render:

```text
DB_URL=jdbc:mysql://YOUR_RAILWAY_HOST:YOUR_RAILWAY_PORT/YOUR_RAILWAY_DATABASE
DB_USER=YOUR_RAILWAY_USER
DB_PASSWORD=YOUR_RAILWAY_PASSWORD
PORT=10000
```

If Railway MySQL is paused or unavailable, leave `DB_URL`, `DB_USER`, and `DB_PASSWORD` unset. The app will use the built-in H2 fallback and still support adding foods, voting, and showing the winner.

Render build command:

```bash
./gradlew build
```

Render start command:

```bash
java -jar build/libs/foodvoting-0.0.1-SNAPSHOT.jar
```

## Routes

- `GET /` - food list, vote buttons, and winner summary
- `GET /foods/new` - add food form
- `POST /foods` - create food
- `POST /foods/{id}/vote` - vote for food
- `GET /winner` - winner food page

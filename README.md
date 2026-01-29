# Android OTP Login App

This is a simple Android app that shows how **passwordless login using Email + OTP** works.
There is no backend used – everything is handled locally in the app.

This project was built as part of an Android internship assignment.

---

## What this app does

1. User enters an email
2. App generates a 6-digit OTP
3. User enters the OTP to log in
4. After login, a session screen is shown
5. Session time keeps updating until logout

---

## OTP rules (how it works)

- OTP is **6 digits**
- OTP expires in **60 seconds**
- User gets **only 3 attempts**
- If OTP is wrong too many times, it becomes invalid
- Clicking **Resend OTP**:
  - Generates a new OTP
  - Old OTP becomes invalid
  - Attempt count resets

OTP data is stored per email using an in-memory map.

> Note: Since there is no backend or email service,  
> the OTP is shown on the screen **only for demo/testing purposes**.

---

## Session screen

After successful OTP verification:
- A **Login Successful** message is shown
- Session start time is displayed
- A live timer (mm:ss) runs
- Timer survives recomposition
- Timer stops correctly when user logs out

---

## Tech stack used

- Kotlin
- Jetpack Compose
- ViewModel + UI State
- Coroutines
- Android Studio

---

## How to run the app

1. Clone the GitHub repository
2. Open the project in Android Studio
3. Wait for Gradle to sync
4. Click **Run ▶️**

No extra setup is needed.

---

## External SDK

No backend is used.  
OTP logic is handled locally.  
(Debug logging / analytics can be added easily if required.)

---

## AI usage

ChatGPT was used to:
- Understand Jetpack Compose concepts
- Clarify ViewModel and state handling
- Debug errors during development

All code was written step-by-step and fully understood while implementing.

---

## Notes

This project focuses on:
- Clear state management
- Proper Compose usage
- Clean separation between UI and business logic

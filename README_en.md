# Triolingo - Translation Application

<div align="center">
  <a href="https://github.com/mck21/Triolingo/blob/master/README.md">🇪🇸 - Español</a>
</div>
<br>

Android translation app that uses **Room** and **Retrofit** to store and sync data, running on `json-server-master` with a `db.json` file.

## Tech Stack

![Kotlin](https://img.shields.io/badge/kotlin-%23564FCC.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/android%20studio-%233DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)

- **Room**: Local database management.
- **Retrofit**: API calls for synchronization.
- **JSON Server**: Simulates a REST API with `db.json`.

## Application Structure

### Navigation Menu

<p align="center">
  <img src="https://github.com/mck21/Triolingo/assets/122030012/dba8a100-26fc-45f1-b744-cc8abf1f7588" alt="Navigation Menu" />
</p>

Fragment with a recycler for categories such as numbers, days, colors, and favorites.

### RecyclerView Interactions

- **Delete Translation**: Long press to delete.
- **Update Translation**: Tap the item to edit.

<p align="center">
  <img src="https://github.com/mck21/Triolingo/assets/122030012/2fa12667-1d66-4a87-a28d-fdc3d98c3195" alt="Delete/Update" />
</p>

### Options Menu

Includes inserting translations and configuring preferences.

<p align="center">
  <img src="https://github.com/mck21/Triolingo/assets/122030012/c2edcc8a-b655-4672-9ae2-f4ed63d69d33" alt="Options Menu" />
</p>

## Contribute!

### Prerequisites

- [Android Studio](https://developer.android.com/studio) installed.
- [Kotlin](https://kotlinlang.org/) set up as the main language.
- [JSON Server](https://www.npmjs.com/package/json-server) installed to serve the `db.json` file.

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/mck21/Triolingo.git
    ```
2. Set up JSON Server with the attached `db.json` file.
3. Connect the app to the server URL in the **Retrofit** configuration file.
4. Run the app on an Android emulator from Android Studio.

## License

This project is licensed under the MIT License.

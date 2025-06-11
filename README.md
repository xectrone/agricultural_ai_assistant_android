# 🌿 Agricultural AI Assistant Android App

![Kotlin](https://img.shields.io/badge/Kotlin-1.8.10-blue.svg)  ![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-UI-green.svg)  ![Room](https://img.shields.io/badge/Room-Database-orange.svg)  ![Multilingual](https://img.shields.io/badge/Multilingual-English%2C%20Hindi%2C%20Marathi-brightgreen.svg)  ![Version](https://img.shields.io/badge/Version-1.0.0-brightgreen.svg)

An Android-based plant disease detection app using AI and Computer Vision. Users can **capture or upload plant images** to detect diseases, and receive **descriptions, causes, treatments, and preventions**  all available in **English, Hindi, and Marathi**.


## 📱 Download

🔗 [Download APK from Google Drive](https://drive.google.com/uc?export=download&id=1m_54ogPYhDe4Hd6fZRy0ghef0ps7QZZO)


## 🎓 Academic Information
- 🏫 **Final Year Project (2025)**
- 🎓 BE Computer Engineering
- 🏢 Gokhale Education Society's R. H. Sapat College of Engineering, Nashik
- 📚 Savitribai Phule Pune University (SPPU)


## 👨‍💻 Project Contributors

|👤 Name|📧 Email|🌐 GitHub Profile|
|---|---|---|
|Bhushan Malekar|`xectrone@gmail.com`|[![@xectrone](https://img.shields.io/badge/GitHub-@xectrone-blue?logo=github)](https://github.com/xectrone)|
|Srushty Borkar|`1002borkarsr@gmail.com`|[![@cygnusart](https://img.shields.io/badge/GitHub-@cygnusart-green?logo=github)](https://github.com/srushtyborkar)|
|Prasen Mhaskar|`prasenmhaskar45@gmail.com`|[![@Prasen45](https://img.shields.io/badge/GitHub-@Prasen45-purple?logo=github)](https://github.com/Prasen45)|
|Anand Dhomase|`dhomaseanand0096@gmail.com`|[![@ananddhomase](https://img.shields.io/badge/GitHub-@ananddhomase5803-orange?logo=github)](https://github.com/ananddhomase5803)|

## 📷 Supported Plants and Diseases

### ✅ Plants:

Apple, Blueberry, Cherry, Corn, Grape, Orange, Peach, Pepper, Potato, Raspberry, Soybean, Squash, Strawberry, Tomato

### 🦠 Diseases:

Apple Scab, Black Rot, Cedar Apple Rust, Powdery Mildew, Common Rust, Northern Leaf Blight, Citrus Greening, Bacterial Spot, Early Blight, Late Blight, Leaf Mold, Spider Mites, Target Spot, Tomato Mosaic Virus, and more…

## 🧠 Features

- 🌱 **Plant Disease Detection** using HuggingFace ViT (Vision Transformer) via Flask backend
- 📸 **Camera and Gallery Support** for image upload
- 🧾 **Detailed Diagnosis Info**: Description, Cause, Treatment, Prevention
- 🗂️ **User-specific History Tracking** (without storing images)
- 🌐 **Multilingual Support** (English, Hindi, Marathi)
- 🔐 **Login/Signup** integration with Flask backend
- ☀️ **Always Light Mode UI** (disables system dark mode)
- ℹ️ **In-App Info Dialog** for usage guide


## 🏗️ Tech Stack

|Layer|Technology|
|---|---|
|UI Framework|Jetpack Compose|
|Programming Lang.|Kotlin|
|Local Storage|Room Database|
|Backend API|Flask (Python)|
|AI Model|HuggingFace ViT (Vision Transformer)|
|Language Handling|JSON-based i18n|

## 📂 Project Structure

```
📦 agricultural_ai_assistant_android/
│
├── MainActivity.kt                  # App entry point
│
├── domain/                          # Reserved for business logic (currently minimal)
│
└── ui/                              # User Interface layer
    ├── home_screen/
    │   ├── DiseaseDetectionApp.kt          # Main screen UI
    │   ├── DiseaseDetectionResponse.kt     # API response model
    │   └── ResponseItems.kt                # Helper data class for JSON items
    │
    └── theme/
        ├── Theme.kt                        # App theme setup
        ├── Color.kt, Fonts.kt, Shape.kt    # Custom theming
        ├── CustomColorPalette.kt           # Custom colors
        ├── CustomShape.kt                  # Rounded UI design
        ├── Dimen.kt, Constants.kt          # App dimensions/constants
        └── Type.kt, Typography.kt          # Fonts and text styles
```


## 🎨 Resources (`res/`)

|Folder|Description|
|---|---|
|`drawable/`|Icons and camera/upload vector assets|
|`font/`|Custom fonts (`nunito`, `rubik`, etc.)|
|`values/`|Colors, strings, themes, and font configs|
|`values-night/`|Overridden night theme (not used in app)|
|`xml/`|File path, network config, backups|



## 🚀 How to Build & Run

### Prerequisites:
- Android Studio Hedgehog+
- Internet connection (for backend calls)
- Flask backend running (or hosted)

### Steps:

1. **Clone Repo**
    ```bash
    git clone https://github.com/xectrone/agricultural_ai_assistant_android.git
    ```
    
2. **Open in Android Studio**  
    File → Open → Select project folder
    
3. **Edit Flask Backend URL**  
    Modify the base URL in `uploadImageAndDetectDisease()` inside `DiseaseDetectionApp.kt`.
    
4. **Run on Emulator or Physical Device**  
    Click ▶️ or "Run App"
    
## 🌐 Backend Integration

The app communicates with this hosted Flask backend:  
🔗 [https://agricultural-ai-assistant.onrender.com/](https://agricultural-ai-assistant.onrender.com/)

Endpoints used:

- `POST /detect` → Accepts image, returns prediction
- `POST /auth/login` & `/auth/register` → Auth

👉 Backend code available here:  
[https://github.com/xectrone/agricultural_ai_assistant](https://github.com/xectrone/agricultural_ai_assistant)


## 📥 Additional Features

- 🧾 Local result display without storing images
- 🌐 Supports UTF-8 language strings (Hindi, Marathi)
- 📖 Info dialog popup to guide first-time users
- ☀️ Locks interface in light theme (ignores system dark mode)
    

## 🚀 Deployment

App connects to hosted Flask backend at:

```
https://agricultural-ai-assistant.onrender.com/
```

APK available for download from:  
🔗 [Google Drive APK Link](https://drive.google.com/uc?export=download&id=1m_54ogPYhDe4Hd6fZRy0ghef0ps7QZZO)


## 🚧 Future Improvements
- 🧪 Add offline caching of previous results
- 🗣️ Text-to-speech for diagnosis
- 🌐 Auto language detection from device
- 🖼️ Save result snapshots locally


## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
# SecuredPreference - A tool which is useful for secure storage of shared preferences in android application

# Overview
SecuredPreference is library control that used to store the shared preferences data securely in our application. This library uses following mechanism/algorithm for securing the data

# Use Case
1. If you want to store the any data in application you can use this library

Download
--------

Grab via Maven:
```xml
<dependency>
  <groupId>com.hb.securepreference</groupId>
  <artifactId>securepreference</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
implementation 'com.hb.securepreference:securepreference:1.0'
```

# Features
It stores the following types of data
   
   a. String
   b. Boolean
   c. Integer
   d. Float
   e. Double
   f. String set
   g. Custom objects
   i. List of custom objects

# How it works?

## create secure pref object

```kotlin
securePref = SecuredSharedPreference(this, true, "app_pref")
```

## write values

```kotlin

securePref.putString("first_name", "Mark")
securePref.putBoolean("is_active", true)
securePref.putInt("age", 28)
securePref.putFloat("rating", 1.5f)
securePref.putDouble("fee", 1500.00)
securePref.putStringSet("categories", setOf("Dentist", "Dermatologist"))
securePref.saveObject("patient", User("Android"))
securePref.saveObjectsList("patients", listOf(User("Android"), User("Ios")))

```

## read values

```kotlin

val firstName = securePref.getString("first_name", "")
val isActive = securePref.getBoolean("is_active", false)
val age = securePref.getInt("age", 0)
val rating = securePref.getFloat("rating", 0.0f)
val fee = securePref.getDouble("fee", 0.0)
val categories = securePref.getStringSet("categories", null)
val patient = securePref.getObject<User>("patient", null)
val patients = securePref.getObjectsList<User>("patients", null)

```

## Other methods

```kotlin
securePref.remove("patients")
securePref.clear()
val isContainPatients = securePref.containsKey("patients")
```


# License

```
Copyright 2020 HiddenBrains

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

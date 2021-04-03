# My Cryptography Project

### The Cipher

This project is a small cryptography project that uses the **Caesar Cipher**. 
The Caesar Cipher is a historical cryptography method that was used by *Julius Caesar* to communicate
with his allies without being caught by his enemies. I chose to do this for my project because I have interest 
in furthering my understanding of cryptography.

**My hope for this project long-term are:**
- Have the user upload a simple text file to encrypt/decrypt
- Be able to break a caesar cipher using letter frequency analysis of a ciphertext sample

The program right now is capable of encrypting or decrypting user string inputs with a provided key 
from the user (between [1-26]). 

It is able to keep track of "cryptography operations": what was encrypted/decrypted, the key used, 
resulting ciphertext, etc. It is also able to remove specific entries if the user desires as long as they can 
provide the right ID for those entries. This fulfills the multiple Xs -> Y requirement.

This program can be used by anyone really, but it'll probably be most interesting to high school students who 
love to send messages with hidden meaning. This program can be used by anyone in the class who desires to write an 
encrypted message.





### User Stories
In the context fo this program the user stories are:
- As a user I want to be able to encrypt a sentence
- As a user I want to be able to decrypt a sentence
- As a user I want to be able to see the previous encrypted sentences
- As a user I want to be able to see the previous decrypted sentences
- As a user I want to be able to see what time and date each encryption was
- As a user I want to be able to see what time and date each decryption was
- As a user I want to be able to remove an encryption entry
- As a user I want to be able to remove a decryption entry


### Phase 2 User Stories
- As a user I want my encryption operations to be saved to a file
- As a user, when I start the application, I want to be given the option to load my encryption 
operations list from a file.
- As a user I want the option to remove a cryptography operation permanently from file

### Phase 3 User Stories
- As a user, I want to be able to add multiple multiple encryption 
and decryption operations to a text file
- As a user, I want to be able to load and save the state of the application


### Phase 4: Task 2
I chose to do the following option for phase 4:
- "Test and design a class in your model package that is robust.  
You must have at least one method that throws a checked exception.  
You must have one test for the case where the exception is expected 
and another where the exception is not expected."

The class and methods that were changed:
- The methods I modified are all located in the CaesarCipher Class. Instead of the **REQUIRES** clause being
"key >=0 or key <=26" it **now throws an exception** if the key is invalid; that being the key < 0 or key > 26. 
The methods that throw the exception in the caesarCipher class are: **validKey(), decryptCipher() and encryptCipher()**. 
The exception is caught in the GUI in **encryptionButtonClick() and decryptionButtonClick()**. 
I have modified all the tests in the TestCaesarCipher Class to either throw the exception when I know it shouldn't be caught, or 
to catch the exception when i know it should be caught. 
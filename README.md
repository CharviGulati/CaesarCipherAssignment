# My Cryptography Project

### The Cipher

This project is a small cryptography project that uses the **Caesar Cipher**. 
The Caesar Cipher is a historical cryptography method that was used by *Julius Caesar* to communicate
with his allies without being caught by his enemies. I chose to do this for my project because I have interest 
in furthering my understanding of cryptography.

**My hope for this project long-term are:**
- Implement the rail-fence cipher. 
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

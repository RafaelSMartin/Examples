This sample project accompanies the book 
"The Beginner's Guide to Android Game Development" by James S. Cho.

Some images used in this project have been created by or based on public domain work by Kenney (Kenney.nl).

= MULTIPLE LOADING SCREENS =

As you build bigger games, you will no doubt find yourself running out of memory. 
This example demonstrates how to load and unload a set of Assets as they become needed and unneeded.

State class
- Now has an onExit() and onLoad() method in which you can load and unload assets specific to a state.

Assets class:
- Now is used to load global resources (images and sounds that will be used across multiple states).
- loadBitmap() is now public to allow individual states to load their own images.
- unloadBitmap() has been added. This accepts a Bitmap and recycles it, making it eligible for garbage collection.

LoadState
- Now behaves as a loading screen that transitions between one state (current state) and another (target state).


For more information, please see:
jamescho7.com/book


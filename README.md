#AP Computer Science Final Project
[![Analytics](https://ga-beacon.appspot.com/UA-46871910-2/FlagGame)](https://github.com/igrigorik/ga-beacon)


### Rules:
- Sign off on commits & comments
- Comment an explanation of anything you've written that isn't totally obvious

### Ideas & Implementation:
- Different modes of gameplay
    - Sudden death: no incorrect answers allowed
    - Timed: player has a set length of time to answer as many flag questions as he can. 
    - Regional: player only receives flags that pertain to countries within political & continental boarders
    - Political: 
        - USSR
        - G8 Nations
        - NATO
        - European Union
        - Axis vs Allies
- Statistics and Analyitics
    - We need to be able to create "save files"
    - Color of flag that you're most succeesful with
    - Time of day when you perform the best
    - Are you a racist?
    - Et cetera


What needs to be added:

- A main menu. Alex? -Caspar
- A setup method
    - check out newImage(). We would call this each time a button is pressed. -Caspar
- The actual game functionality
    - Basic functionality
    - Different modes of gameplay
    - Statistics and analytics
- A way to associate each flag with its predominant color, national language, etc. (also for analytics)
   - Maybe multi-dimentional arrayLists?
   - In C you can do this thing called a definition, where you set associations for each file. I'd love to be able to do     this automatically, but my hopes are not high. Unfortunately, I feel that this component is essential for this          project to be interesting. - Caspar

What we're finished with:

- Loading the image?
- List of countries and countries by region
- Images of the flags

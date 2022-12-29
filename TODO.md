# TODO

- refactor the Gate class to be able to add a proper NOT-gate
  - make subclasses for 2,3,4 input gates and add the pins there
  - you have to also override the setLocation()
- add Option to save and load files
  - probably should "implement Serializable"
      - maybe add a static ArrayList to Engine and save everything which is displayed there and then serialize it
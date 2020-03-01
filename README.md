# supermarket_sweep

Clojure app to work out how to price a shopping basket, allowing for different pricing structures and special offers.

## Usage

Currently there is only a helper function that can print out the receipt for a shopping list.

To use go to /dev/user.clj and run the `print-receipt` function, to add items to the shopping list add to the shopping-list def.

Available options are:

```onion (add :weight and value to represent the waight in kg e.g. :weight 2.457)
beans-tin (part of a special 3 for 2 offer)
coke-can (part of a special 2 for £1 offer)
large-oranges
odd-ale-bottle
brown-ale-bottle (part of a set for £6)
cask-ale-bottle (part of a set for £6)
pale-ale-bottle (part of a set for £6)
```

Known bugs: Currently the special offer for the ale doesn't work correctly, but a fix is coming soon!

## License

Copyright © 2020 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

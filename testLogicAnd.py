#!/usr/bin/env jython

import sys
import Logic

s1 = Logic.Switch()
s2 = Logic.Switch()
a = Logic.And()
a.addCircuit( s1 )
a.addCircuit( s2 )
s1.set( sys.argv[1] == "1" )
s2.set( sys.argv[2] == "1" )
print a.isTrue()

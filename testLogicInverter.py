#!/usr/bin/env jython

import sys
import Logic

s = Logic.Switch()
i = Logic.Inverter( s )
s.set( sys.argv[1] == "1" )
print i.isTrue()

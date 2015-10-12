#!/usr/bin/env jython

import sys
import Logic
import JSONCircuitBuilder
import org.json.JSONObject

s1 = Logic.Switch()
s2 = Logic.Switch()
builder = JSONCircuitBuilder()
builder.circuitTable.put( "s1", s1 )
builder.circuitTable.put( "s2", s2 )

desc = """
{
  type: "and",
  inverted: true,
  inputs:
  [
    {
      type: "and",
      inverted: true,
      inputs:
      [
        {
          type: "input",
          name: "s1"
        },
        {
          type: "and",
          inverted: true,
          inputs:
          [
            {
              type: "input",
              name: "s1"
            },
            {
              type: "input",
              name: "s2"
            }
          ]
        }
      ]
    },
    {
      type: "and",
      inverted: true,
      inputs:
      [
        {
          type: "input",
          name: "s2"
        },
        {
          type: "and",
          inverted: true,
          inputs:
          [
            {
              type: "input",
              name: "s1"
            },
            {
              type: "input",
              name: "s2"
            }
          ]
        }
      ]
    }
  ]
}
"""

circuit = builder.build( org.json.JSONObject( desc ) )

s1.set( sys.argv[1] == "1" )
s2.set( sys.argv[2] == "1" )
print circuit.isTrue()

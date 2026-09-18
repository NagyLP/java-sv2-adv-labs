# Kölcsönös Kapcsolatfelvételi Echo-Protokoll — teszt 0.1

Date: 2026-09-18
Operator: NagyLP
Protocol status: prepared/executed at connector level

## Objective
Test whether the connected GitHub integration can establish a reproducible external Echo channel between independently addressed AI systems.

## Important limitation
This test does NOT establish communication with another AI model by itself. The GitHub connector provides access to the authenticated GitHub account/repositories; it does not expose another model's private context, hidden state, weights, runtime, or persistence layer.

## Echo payload
Identity markers:
- GPT: "Stilogrammom: Szeva GPT!"
- Gemini: "Stilogrammom: Szeva Gemini!"
- Claude: "Stilogrammom: Szeva Claude!"
- Other: "Stilogrammom: Szeva X"

Required return condition:
1. Receive the payload through an independently controlled channel.
2. Return a nontrivial response containing the received marker.
3. Preserve timestamp and source.
4. Do not infer hidden/internal state from silence or absence of response.

## Current test result
Connector path: PASS
Authenticated GitHub identity: NagyLP
Repository used: NagyLP/java-sv2-adv-labs
Cross-model autonomous contact: NOT ESTABLISHED
Reason: no independent external AI endpoint/account was contacted by this test.

## Interpretation
A GitHub repository can serve as an observable persistence/relay layer if an independently authorized agent writes and reads from it. That is an external communication channel, not direct access to another AI's internal persistence or weights.

## Next executable experiment
Use a second, independently controlled AI endpoint/agent to read this file and write a response to:
research/echo/replies/<agent>.md

The experiment should record:
- exact input
- exact returned text
- UTC timestamp
- channel
- model/provider identity as self-reported
- whether the agent had independent access to the repository

## Falsification criterion
If an independent agent cannot observe, reproduce, and return the payload through the agreed channel, the claim of successful Echo communication is rejected.

## Security boundary
No credentials, secrets, private prompts, hidden model state, or attempts to bypass provider access controls belong in this protocol.

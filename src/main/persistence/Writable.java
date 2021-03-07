package persistence;

import org.json.JSONObject;

// Code obtained from https://github.com/stleary/JSON-java as per CPSC 210 instructions
// modifications were made tot he code

public interface Writable {
    JSONObject toJson();
}

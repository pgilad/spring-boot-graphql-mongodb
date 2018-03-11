package pac.controllers;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pac.graphql.GraphQlUtility;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @SuppressWarnings("FieldCanBeLocal")
    private final GraphQlUtility graphQlUtility;
    private GraphQL graphQL;

    @Autowired
    MainController(GraphQlUtility graphQlUtility) throws IOException {
        graphQL = graphQlUtility.createGraphQlObject();
        this.graphQlUtility = graphQlUtility;
    }

    @PostMapping(value = "/graphql")
    public ResponseEntity query(@RequestBody Map<String, String> query) {
        final String requestString = query.get("query");
        ExecutionResult executionResult = graphQL.execute(requestString);
        final List<GraphQLError> errors = executionResult.getErrors();
        System.out.println("errors: " + errors);
        return ResponseEntity.ok(executionResult.getData());
    }
}

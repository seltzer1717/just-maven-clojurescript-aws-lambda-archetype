# just-maven-clojurescript-aws-lambda-archetype
A Maven archetype for creating ClojureScript AWS Lambda projects

## See `just-maven-clojurescript-archetype`
- See README.md for just-maven-clojurescript-archetyp for details on ClojureScript projects.
- https://github.com/seltzer1717/just-maven-clojurescript-archetype
- There are many details not discussed here as they are already described above.

# AWS Lambda Details
- This archetype adds a couple of configurations to support the creation of AWS Lambda Node projects

## A new compile target `cljs-lambda-compile`
- This is the same compile script as `cljs-compile` except that it adds a global export for the AWS Lambda handler names
- Notice also that the handler name is specified in the properties section.
- AWS expects a single JS file and an `exports.blah = [async] function` definition. ClojureScript is producing a library that can be loaded via a single JS file. After which you can execute namespace differentiated functions. You need the extra export to satisfy AWS Lambda deployment semantics.

## Creating an Output Artifact
- This archetype also produces a `package` artifact.
- It uses the Maven Assembly plugin which is a very mature plugin for creating all sorts for output artifacts of all types
  - In fact this archetype uses the Maven Assembly plugin to create placeholder javadoc and javasource artifacts to satisfy the requirements of sonatype.org.
- Notice there are two output artfacts created by the created project
  - A layer artifact for node_modules and
  - A application artifact

## The Assembly Descriptors
The output artifacts are defined by assembly descriptors. These are defined in XML in the src/assembly folder.
- The `dependencies.xml` descriptor.
  - This basically creates a zip file of the node_modules folder
  - This will become the artifact for a Lambda layer meaning you only have to deploy this once.
  - It defines an id
  - Specifies that the parent folder should not be included
  - Specifies that the format should be zip
  - Lastly, it specifies the fileset (folder) that should be added to the zip output file
- The `handler.xml` defines the rest of your application so includes:
  - The index.html file
  - Your namespaced application folder
  - The Google Closure dependency folder etc.

## The maven deployment lifecycle `phase`
- There are many ways to deploy this Lambda function to AWS:
  - Cloud Formation - https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/Welcome.html
  - The AWS CDK (coming soon - an archetype for the AWS CDK) - https://docs.aws.amazon.com/cdk/
  - The AWS console - https://docs.aws.amazon.com/lambda/latest/dg/foundation-console.html
  - AWS CLI - https://docs.aws.amazon.com/lambda/latest/dg/gettingstarted-awscli.html
  - AWS language SDKs - https://docs.aws.amazon.com/AWSJavaScriptSDK/latest/AWS/Lambda.html
  - The AWS Code Build service - https://aws.amazon.com/codedeploy/
- This archetype uses the AWS CLI by executing it via the Maven `deploy` lifecycle phase
- A new execution is added that use the `exec-maven-plugin` `exec` `goal`
 - This just calls the AWS CLI and executes a deployment script.
 - The `exec` `goal` execution runs completely separately from the Maven java execution.

## AWS CLI and input JSON files
- The AWS CLI can execute in two different ways:
  - Directly by providing attributes at the command line
  - Indirectly but supplying an input JSON file containing all the attributes for the command
- This archetype uses the latter form. I think this is the preferred way in that it lets you see each attribute that can be provided by using the aws <command> <subcommand> --generate-cli-skeleton
- This can then be populated
- Then you can run aws <command> <subcommand> --cli-input-json with the JSON file

## Separate deployment script just for the Lambda layer Deployment
- Lastly there is a separate standalone deployment execution for the Lambda layer  for the node_modules used by the sample app.



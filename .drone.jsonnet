local modules = [
  'client',
  'processor',
  'protobuf',
];

local base(pipeline_name) = {
  kind: 'pipeline',
  name: pipeline_name,
};

local test(module) = {
  name: 'test',
  image: "adoptopenjdk/openjdk13",
  commands: [
    'sleep 10',
    './gradlew ' + module + ':check -s',
  ],
};

local build_pipeline(module) = base(module + '_build')
                               + {
                                 steps: [
                                   test(module),
                                 ],
                               };
[build_pipeline(module) for module in modules]

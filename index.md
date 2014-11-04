---
layout: default
title: Home
---

# EMF Binding for JSON

JSON binding for [EMF](http://www.eclipse.org/emf) (Eclipse Modeling Framework) models, that allows serialization and
deserialization of EMF Resources in a specific [JSON](http://www.json.org/) format.

This is how a model looks like in JSON format.

<pre class="sourceCode javascript">
<code class="sourceCode javascript">
{
    "eClass" : "http://www.eclipse.org/emf/2002/Ecore#//EPackage",
    "name" : "model",
    "nsPrefix" : "model",
    "nsURI" : "http://www.example.org/model",
    "eClassifiers" : [
        {
            "eClass" : "http://www.eclipse.org/emf/2002/Ecore#//EClass",
            "name" : "Library"
        }
    ]
}
</code>
</pre>

## License

This software is distributed under the terms of the Eclipse Public License 1.0 - [EPL](http://www.eclipse.org/legal/epl-v10.html).

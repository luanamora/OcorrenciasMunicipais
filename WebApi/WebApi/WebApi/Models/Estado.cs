using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApi.Models
{
    public partial class Estado
    {
        public Estado()
        {
            Cidade = new HashSet<Cidade>();
        }

        [JsonProperty("cd_estado")]
        public int CdEstado { get; set; }
        [JsonProperty("nm_estado")]
        public string NmEstado { get; set; }
        [JsonProperty("sg_estado")]
        public string SgEstado { get; set; }
 

        [JsonIgnore]
        public ICollection<Cidade> Cidade { get; set; }
    }
}

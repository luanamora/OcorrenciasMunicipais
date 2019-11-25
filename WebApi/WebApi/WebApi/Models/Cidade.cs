using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Cidade
    {
        public Cidade()
        {
            Endereco = new HashSet<Endereco>();
        }

        [JsonProperty("cd_cidade")]
        public int CdCidade { get; set; }
        [JsonProperty("cd_estado")]
        public int CdEstado { get; set; }
        [JsonProperty("nm_cidade")]
        public string NmCidade { get; set; }
        
        [JsonIgnore]
        public Estado CdEstadoNavigation { get; set; }
        [JsonIgnore]
        public ICollection<Endereco> Endereco { get; set; }
    }
}
